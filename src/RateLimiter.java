public class RateLimiter  {

    public static void main(String[] args) throws Exception {

        RateLimitTechnique rateLimitTechnique = new FixedBucketCounter(2,2);
        RateLimitTechnique rateLimitTechnique1 = new FixedBucketCounter(10,10);


        RateLimitingContext rateLimitingContext = new RateLimitingContext(rateLimitTechnique);



        String userId1 = "123";
        String userId2 = "456";

        for(int i=0;i<=30;i++) {
            if ((rateLimitingContext.rateLimitTechnique.approveRequest(userId1))) {
                System.out.println(userId1 + " approved");

            } else  {
                System.out.println(userId1 + " not approved");
            }
//            if ((rateLimitTechnique1.approveRequest(userId2))) {
//                System.out.println(userId2 + " approved");
//
//            } else  {
//                System.out.println(userId2 + " not approved");
//            }
            Thread.sleep(200);
         }

        System.out.println("sleeping 2 seconds");
        Thread.sleep(2000);

//        for(int i=0;i<=10;i++) {
//            if ((rateLimitTechnique.approveRequest(userId1))) {
//                System.out.println(userId1 + " approved");
//            } else  {
//                System.out.println(userId1 + " not approved");
//            }
//            Thread.sleep(150);
//        }
    }


}
