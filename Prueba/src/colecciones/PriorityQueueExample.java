package colecciones;

import java.util.PriorityQueue;

public class PriorityQueueExample {

	public static void main(String[] args) {

		PriorityQueue<Employee> employeePriorityQueue = new PriorityQueue<>();
		
		// Add items to the Priority Queue
		employeePriorityQueue.add(new Employee("Rajeev", 100000.00));
		employeePriorityQueue.add(new Employee("Chris", 145000.00));
		employeePriorityQueue.add(new Employee("Alberto", 115000.00));
		employeePriorityQueue.add(new Employee("Andrea", 115000.00));
		employeePriorityQueue.add(new Employee("Juanpe", 167000.00));
		employeePriorityQueue.add(new Employee("Angela", 177000.00));
<<<<<<< HEAD
		employeePriorityQueue.add(new Employee("Guillermo", 169000.00));
		employeePriorityQueue.add(new Employee("Jack", 167000.00));

		/*
		 * The compareTo() method implemented in the Employee class is used to determine
		 * in what order the objects should be dequeued.
		 */
=======
		employeePriorityQueue.add(new Employee("Joel", 77000.00));
		employeePriorityQueue.add(new Employee("Lorenzo", 86000.00));
		employeePriorityQueue.add(new Employee("Vicky", 147000.00));
		employeePriorityQueue.add(new Employee("Guillermo", 169000.00));
		employeePriorityQueue.add(new Employee("Jack", 167000.00));

>>>>>>> branch 'main' of https://github.com/twlster/LaboratorioFpDual.git
		while (!employeePriorityQueue.isEmpty()) {
			System.out.println(employeePriorityQueue.remove());
		}

	}

}
