import java.util.concurrent.ConcurrentHashMap;

class FixedBucketCounter implements RateLimitTechnique {

    private final int capacity;
    private final int refillRatePerSecond;
    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    class Bucket {
        double tokens;         // allow fractional tokens
        long lastRefillTime;   // in nanoseconds
        Bucket(int capacity) {
            this.tokens = capacity;
            this.lastRefillTime = System.nanoTime(); // use nano everywhere
        }
    }

    FixedBucketCounter(int cap, int refillRatePerSec) {
        this.capacity = cap;
        this.refillRatePerSecond = refillRatePerSec;
    }

    @Override
    public boolean approveRequest(String userId) {
        Bucket bucket = buckets.computeIfAbsent(userId, k -> new Bucket(capacity));
        synchronized (bucket) {
            refill(bucket);
            if (bucket.tokens >= 1) {
                bucket.tokens -= 1;
                return true;
            }
            return false;
        }
    }

    private void refill(Bucket bucket) {
        long now = System.nanoTime();
        double elapsedSeconds = (now - bucket.lastRefillTime) / 1e9;
        double tokensToAdd = elapsedSeconds * refillRatePerSecond;

        if (tokensToAdd > 0) {
            bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
            bucket.lastRefillTime = now;
        }
    }
}
