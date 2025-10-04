public interface RateLimitTechnique {
    public boolean approveRequest(String userId);
}
