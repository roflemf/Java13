import java.util.ArrayList;

/* wait()와 notify() 스레드 스케줄링 메서드 사용 */

class Table15{
	String[] dishnames = {"donut", "donut", "burger"};
	final int MAX_FOOD = 6;
	private ArrayList<String> dishes = new ArrayList<>();

	//동기화 된 음식 추가
	public synchronized void add(String dish) {
		while(dishes.size() >= MAX_FOOD) {
			String name = Thread.currentThread().getName();
			System.out.println(name + " is waiting");

			try {
				wait(); //요리사 쓰레드를 기다리게 한다.
				Thread.sleep(500);
			}catch(InterruptedException ie) {}

		}//while
		dishes.add(dish);
		notify();//기다리고 있는 소비자를 깨워서 음식을 먹게 함.
		System.out.println("추가된 음식 목록 : " + dishes.toString());
	}//add()

	//음식 제거
	public void remove(String dishName) {
		synchronized (this) {
			String name = Thread.currentThread().getName();

			while(dishes.size() ==0) {//테이블에 음식이 없을 경우
				System.out.println(name + " is waiting");

				try {
					wait();//소비자를 대기실에서 기다리게 한다.
					Thread.sleep(500);
				}catch(InterruptedException ie) {}
			}//while

			while(true) {
				for(int i =0; i<dishes.size();i++) {
					if(dishName.equals(dishes.get(i)))
						dishes.remove(i);//음식 제거
					notify();//대기실에 있는 랜덤 한명을 깨운다.
					return; //끝냄
				}//for
				try {
					System.out.println(name + " is waiting");
					wait();//음식이 없어서 소비자를 대기실로 보낸다.
					Thread.sleep(500);
				}catch (InterruptedException ie) {}
			}//while
		}//synchronized()
	}//remove()

	public int dishNum() { return dishnames.length;}
}//Table15 class

//소비자 쓰레드
class Customer15 implements Runnable{

	private Table15 table;
	private String food;

	Customer15() {}
	Customer15(Table15 table, String food) {
		this.table = table;
		this.food = food;
	}

	@Override
	public void run() {
		while(true) {
			try {Thread.sleep(100);}catch(InterruptedException ie) {}
			String name = Thread.currentThread().getName();

			table.remove(food);
			System.out.println(name + " ate a " + food);

		}//while
	}//run()

}//Customer15 class

//요리사 스레드
class Cook15 implements Runnable{

	private Table15 table;

	Cook15() {}
	Cook15(Table15 table){
		
		this.table = table;
	}

	@Override
	public void run() {
		while(true) {
			int idx = (int)(Math.random()*table.dishNum());
			table.add(table.dishnames[idx]);

			try {Thread.sleep(10);}catch (InterruptedException ie) {}
		}//while()
	}//run()

}//Cook15 class

public class ThreadTest15 {
	public static void main(String[] args) throws Exception {
		Table15 table = new Table15();
		
		new Thread(new Cook15(table),"cook15").start();
		
		new Thread(new Customer15(table,"donut"), "customer01").start();
		new Thread(new Customer15(table,"burger"), "customer02").start();
		
		Thread.sleep(2000);//2초 뒤에
		System.exit(0);//정상종료

	}
}
