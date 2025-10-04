class Person {
    private int a;
    private int b;
    private int c;
    private String d;

    Person(Builder b) {
        this.a = b.getA();
        this.b = b.getB();
        this.c = b.getC();
        this.d = b.getD();
    }

    @Override
    public String toString() {
        return "Person{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d='" + d + '\'' +
                '}';
    }

    static class Builder {
        private int a;
        private int b;
        private int c;
        private String d;

        public Builder setA(int a) {
            this.a = a;
            return this;
        }

        public Builder setB(int b) {
            this.b = b;
            return this;

        }

        public Builder setC(int c) {
            this.c = c;
            return this;

        }

        public Builder setD(String d) {
            this.d = d;
            return this;

        }

        public int getA() {
            return this.a;
        }

        public int getB() {
            return this.b;

        }

        public int getC() {
            return this.c;

        }

        public String getD() {
            return this.d;
        }

        public Person build() {
            return new Person(this);
        }


    }




}

public class BuilderPatternSample {
    public static void main(String[] args) {
        Person person = new Person.Builder().setA(5).setB(6).setC(6).build();
        System.out.println(person);
    }
}


/*

/authorization?authCode = 18

18

if kyc verified
if current location is not banned
if



gameplay-topic

5 partitions



3 instances consumer service that is consuming the event (CG = 1)
CG = 2, 3 instances in that consumer group

evert partition also replicas and a leader


acks = 1

P1 -> CG2 C1
P2 -> CG2 C2  DB
P3 -> CG1 C1

CG2 C3 -> idle




backpressure -->

1. db choking
2. increase the partitions, increase the instances

max.byte.


interceptor (interceptors)



check jwt token in this
.permit()



client -> does a login -> send jwt back (server 1)
client -> sends a request to list all the transaction he has done -> all request will contain this jwt token
-> intercetor intercepts this now it will check decrypting and signature -> if everything is correct -> permit
->






 */