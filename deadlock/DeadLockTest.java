package deadlock;

/**
 * @author iaggar 
 * Run DeadLockTest class and observe that all threads are blocked.
 * Steps to analyze :-
 * 	1.) Find the PID of running java process (javaw.exe) using Windows Task Manager.
 *  2.) Execute the command
 * 		C:\Program Files\Java\jdk1.8.0_74\bin>jmap -dump:live,format=b,file=DeadLockTest.hprof 63420
 *		Dumping heap to C:\Program Files\Java\jdk1.8.0_74\bin\DeadLockTest.hprof ...
 *		Heap dump file created
 *	3.) Download VisualVM eclipse plugin
 *		https://visualvm.java.net/eclipse-launcher.html
 *		Install the VisualVM plugin in Eclipse
 *		https://blog.idrsolutions.com/2013/05/setting-up-visualvm-in-under-5-minutes/
 *		Eclipse will restart itself
 *	4.) Now simply drag and drop the DeadLockTest.hprof file in eclipse and analyze the file.
 *		Click on button to show each thread's name and complete stack.
 *	5.) 
 *		Object / Stack Frame                                          |Name    | Shallow Heap | Retained Heap |Context Class Loader                          |Is Daemon
---------------------------------------------------------------------------------------------------------------------------------------------------------------
deadlock.MyThread @ 0x6c9200c30                               |Thread-0|          128 |           240 |sun.misc.Launcher$AppClassLoader @ 0x6c9201ad0|false
'- at deadlock.MyThread.run()V (MyThread.java:22)             |        |              |               |                                              |
   |- <local> deadlock.MyThread @ 0x6c9200c30  Thread-0 Thread|        |          128 |           240 |                                              |
   |- <local> java.lang.Object @ 0x6c9200a80 Busy Monitor     |        |           16 |            16 |                                              |
   |- <local> java.lang.Object @ 0x6c9200c20 Busy Monitor     |        |           16 |            16 |                                              |
   '- Total: 3 entries                                        |        |              |               |                                              |
deadlock.MyThread @ 0x6c9200aa0                               |Thread-1|          128 |           240 |sun.misc.Launcher$AppClassLoader @ 0x6c9201ad0|false
'- at deadlock.MyThread.run()V (MyThread.java:22)             |        |              |               |                                              |
   |- <local> deadlock.MyThread @ 0x6c9200aa0  Thread-1 Thread|        |          128 |           240 |                                              |
   |- <local> java.lang.Object @ 0x6c9200c20 Busy Monitor     |        |           16 |            16 |                                              |
   |- <local> java.lang.Object @ 0x6c9200a90 Busy Monitor     |        |           16 |            16 |                                              |
   '- Total: 3 entries                                        |        |              |               |                                              |
deadlock.MyThread @ 0x6c9200900                               |Thread-2|          128 |           240 |sun.misc.Launcher$AppClassLoader @ 0x6c9201ad0|false
'- at deadlock.MyThread.run()V (MyThread.java:22)             |        |              |               |                                              |
   |- <local> deadlock.MyThread @ 0x6c9200900  Thread-2 Thread|        |          128 |           240 |                                              |
   |- <local> java.lang.Object @ 0x6c9200a90 Busy Monitor     |        |           16 |            16 |                                              |
   |- <local> java.lang.Object @ 0x6c9200a80 Busy Monitor     |        |           16 |            16 |                                              |
   '- Total: 3 entries                                        |        |              |               |                                              |
---------------------------------------------------------------------------------------------------------------------------------------------------------------
 *
 *	Output is clearing showing that all the three threads are in deadlock state.
 *	
 *	----------------------------------OR---------------------------------------
 *
 *	1.) Find the PID of running java process (javaw.exe) using Windows Task Manager.
 *	2.) Execute the command
 *			C:\Program Files\Java\jdk1.8.0_74\bin>jstack 57232
 *		It will generate the complete report file. Saved the output in deadlock_report.txt file 
 *		It is clearing showing - Found 1 deadlock.
 *
 *	----------------------------------OR---------------------------------------
 *
 *	1.) Find the PID of running java process (javaw.exe) using Windows Task Manager.
 *	2.) Execute the command
 *			C:\Program Files\Java\jdk1.8.0_74\bin>jcmd 62684 DeadLockTest.print
 *			62684:
 *				java.lang.IllegalArgumentException: Unknown diagnostic command
 *
 */
public class DeadLockTest {

	/**
	 * @param s
	 */
	public static void main(String s[]) {

		// Creating multiple objects
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();

		// Creating multiple threads, each thread including two objects
		Thread thread1 = new MyThread(obj1, obj2);
		Thread thread2 = new MyThread(obj2, obj3);
		Thread thread3 = new MyThread(obj3, obj1);

		// Starting all threads
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
