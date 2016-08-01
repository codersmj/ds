package concurrency.semaphore;


/**
 * Main class, for testing CustomSemaphore
 */
class CustomSemaphoreTest {
 
    static int SharedValue=0;
    
    public static void main(String[] args) {
           CustomSemaphore semaphore=new CustomSemaphore(1);
           System.out.println("Semaphore with 1 permit has been created");
           
           IncrementThread incrementThread=new IncrementThread(semaphore);
           new Thread(incrementThread,"incrementThread").start();
           
           DecrementThread decrementThread=new DecrementThread(semaphore);
           new Thread(decrementThread,"decrementThread").start();
           
    }
}
 

 
class IncrementThread implements Runnable{
 
    CustomSemaphore customSemaphore;
    
    public IncrementThread(CustomSemaphore customSemaphore) {
           this.customSemaphore=customSemaphore;        
    }
    
    public void run(){
           System.out.println(Thread.currentThread().getName()+
                        " is waiting for permit");
           try {
                  customSemaphore.acquire();
                  System.out.println(Thread.currentThread().getName()+
                               " has got permit");
           
                  for(int i=0;i<5;i++){
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+
                                      " > "+CustomSemaphoreTest.SharedValue++);
                  }
                  
           } catch (InterruptedException e) {
                  e.printStackTrace();
           }
           
           System.out.println(Thread.currentThread().getName()+
                        " has released permit");
           customSemaphore.release();
    
    }
    
}

 
class DecrementThread implements Runnable{
 
    CustomSemaphore customSemaphore;
    public DecrementThread(CustomSemaphore customSemaphore){
           this.customSemaphore=customSemaphore;
    }
    
    public void run(){
           System.out.println(Thread.currentThread().getName()+
                        " is waiting for permit");
           
           try {
                  customSemaphore.acquire();
                  System.out.println(Thread.currentThread().getName()+
                               " has got permit");
           
                  for(int i=0;i<5;i++){          
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+
                                      " >"+CustomSemaphoreTest.SharedValue--);
                  }
                  
           } catch (InterruptedException e) {
                  e.printStackTrace();
           }
           
           
           System.out.println(Thread.currentThread().getName()+
                        " has released permit");
           customSemaphore.release();
           
           
    }
    
}