package Thread_State;

/*
    데이터를 저장하는 생산자 스레드와 데이터를 소비하는 소비자 스레드가 각자의 역할을 교대로 수행하는 내용
    생산자 스레드는 소비자 스레드가 읽기 전에 새로운 데이터를 두 번 생성하면 안 되고,
    소비자 스레드는 생산자 스레드가 새로운 데이터를 생성하기 전에 이전 데이터를 두 번 읽으면 안 된다.
 */

public class Thread_Synchronized2 {

    public static void main(String[] args) {
        DataBox dataBox = new DataBox();

        ProducerThread producerThread = new ProducerThread(dataBox);
        ConsumerThread consumerThread = new ConsumerThread(dataBox);

        producerThread.start();
        consumerThread.start();

    }

    private static class DataBox {
        private String data;

        //  소비자 스레드의 임계 영역
        public synchronized String getData() {
            if (this.data == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String returnValue = data;
            System.out.println("ConsumerThread가 읽은 데이터 : " + returnValue);
            data = null;
            notify();
            return returnValue;
        }

        // 생산자 스레드의 임계 영역
        public synchronized void setData(String data) {
            if (this.data != null) {    // 데이터가 없어야 생산이 가능
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.data = data;
            System.out.println("ProducerThread가 생산한 데이터 : " + data);
            notify();
        }
    }

    private static class ProducerThread extends Thread{
        private DataBox dataBox;

        public ProducerThread(DataBox dataBox) {
            this.dataBox = dataBox;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                String data = "Data-" + i;
                dataBox.setData(data);
            }
        }
    }

    private static class ConsumerThread extends Thread {
        private DataBox dataBox;

        public ConsumerThread(DataBox dataBox) {
            this.dataBox = dataBox;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                String data = dataBox.getData();
            }
        }
    }
}
