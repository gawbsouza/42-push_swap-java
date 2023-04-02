package br.dev.gawbsouza.pushswap.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * The Push Swap game class.<br><br>
 * 
 * This class has the basic structure of the game to be used with an external 
 * game solver strategy.<br><br>
 * 
 * It has the main movements SA, SB, SS, PA, PB, RA, RB, RR, RRA, RRB and RRR, 
 * in addition to some auxiliary methods that facilitate the construction of 
 * solvers.
 */
public final class PushSwap {

	private final int INITIAL_MOVEMENTS_CAPACITY = 128;

	private Stack<Integer> a;
	private Stack<Integer> b;
	private List<Movements> movements;

	/**
	 * PushSwap constructor with initial values from stack A.
	 * @param numbers starting numbers from stack A
	 */
	public PushSwap(int... numbers) {

		a = new Stack<>();
		b = new Stack<>();

		movements = new ArrayList<>(INITIAL_MOVEMENTS_CAPACITY);
		a.ensureCapacity(numbers.length);

		for (int i = numbers.length - 1; i >= 0; i--) {
			a.push(numbers[i]);
		}
	}

	/**
	 * Gets a representation of the internal A stack.
	 * @return copy of the internal A stack
	 */
	public Stack<Integer> getStackA() {
		var copy = new Stack<Integer>();
		copy.addAll(a);
		return copy;
	}

	/**
	 * Gets a representation of the internal B stack.
	 * @return copy of the internal B stack
	 */
	public Stack<Integer> getStackB() {
		var copy = new Stack<Integer>();
		copy.addAll(b);
		return copy;
	}

	/**
	 * Gets a list of executed movements.
	 * @return a list of executed movements
	 */
	public List<Movements> getMovements() {
		var copy = new ArrayList<Movements>();
		copy.addAll(movements);
		return copy;
	}
	
	/**
	 * Returns true if the "game" was successfully completed. <br>
	 * Checks whether stack A is sorted in ascending order and whether stack B 
	 * is empty.
	 * @return true if the "game" was successfully completed.
	 */
	public boolean isSolved() {
		return (b.size() == 0 && isStackOrdered(a));
	}

	/**
	 * Checks whether a stack is sorted in ascending order.
	 * @param stack to be checked
	 * @return true if stack is ordered
	 */
	public static boolean isStackOrdered(Stack<Integer> stack) {
		
		if (hasAtMostOneItemOnStack(stack)) return true;
		
		for(int i = stack.size() - 2; i >= 0; i--) {
			if (stack.get(i) < stack.get(i + 1))
				return false;
		}
		
		return true;
	}

	/**
	 * <strong>SA movement</strong>
	 * <br><br>
	 * Swap the first with the second item from A stack.
	 */
	public void sa() {
		movements.add(Movements.SA);
		swap(a);
	}
	
	/**
	 * <strong>SB movement</strong>
	 * <br><br>
	 * Swap the first with the second item from B stack
	 */
	public void sb() {
		movements.add(Movements.SB);
		swap(b);
	}
	
	/**
	 * <strong>SS movement</strong>
	 * <br><br>
	 * Swap the first with the second item from A and B stack 
	 * simultaneously.
	 */
	public void ss() {
		movements.add(Movements.SS);
		swap(a);
		swap(b);
	}
	
	/**
	 * <strong>PA movement</strong>
	 * <br><br>
	 * Push the first item from B stack into A stack. <br>
	 * If stack B is empty no item is pushed, but movement is still counted.
	 */
	public void pa() {
		movements.add(Movements.PA);
		popFromPushTo(b, a);
	}
	
	/**
	 * <strong>PB movement</strong>
	 * <br><br>
	 * Push the first item from A stack into B stack. <br>
	 * If stack A is empty no item is pushed, but movement is still counted.
	 */
	public void pb() {
		movements.add(Movements.PB);
		popFromPushTo(a, b);
	}

	/**
	 * <strong>RA movement</strong>
	 * <br><br>
	 * Pushes the first item to the bottom of the A stack, rotating the stack 
	 * items upwards.
	 */
	public void ra() {
		movements.add(Movements.RA);
		rotateUp(a);
	}
	
	/**
	 * <strong>RB movement</strong>
	 * <br><br>
	 * Pushes the first item to the bottom of the B stack, rotating the stack 
	 * items upwards.
	 */
	public void rb() {
		movements.add(Movements.RB);
		rotateUp(b);
	}
	
	/**
	 * <strong>RR movement</strong>
	 * <br><br>
	 * Pushes the first item to the bottom of stack A and stack B at the same 
	 * time, rotating stack items upwards.
	 */
	public void rr() {
		movements.add(Movements.RR);
		rotateUp(a);
		rotateUp(b);
	}

	/**
	 * <strong>RA movement</strong>
	 * <br><br>
	 * Pushes the last item to the top of the A stack, rotating the stack items
	 * downwards.
	 */
	public void rra() {
		movements.add(Movements.RRA);
		rotateDown(a);
	}
	
	/**
	 * <strong>RB movement</strong>
	 * <br><br>
	 * Pushes the last item to the top of the B stack, rotating the stack items
	 * downwards.
	 */
	public void rrb() {
		movements.add(Movements.RRB);
		rotateDown(b);
	}
	
	/**
	 * <strong>RRR movement</strong>
	 * <br><br>
	 * Pushes the last item to the top of stack A and stack B simultaneously, 
	 * rotating stack items downwards.
	 */
	public void rrr() {
		movements.add(Movements.RRR);
		rotateDown(a);
		rotateDown(b);
	}
	
	private void swap(Stack<Integer> stack) {
		
		if (hasAtMostOneItemOnStack(stack)) return;
		
		int secondItemIndex = stack.size() - 2;
		int firstItemIndex = stack.size() - 1;

		int secondItemValue = stack.get(secondItemIndex);
		int firstItemValue = stack.get(firstItemIndex);

		stack.set(firstItemIndex, secondItemValue);
		stack.set(secondItemIndex, firstItemValue);
	}
	
	private void rotateUp(Stack<Integer> stack) {

		if (hasAtMostOneItemOnStack(stack)) return;

		int firstItem = stack.pop();
		
		stack.add(0, firstItem);
	}
	
	private void rotateDown(Stack<Integer> stack) {
		
		if (hasAtMostOneItemOnStack(stack)) return;
		
		int lastItemIndex = 0;
		int lastItem = stack.get(lastItemIndex);
		
		stack.remove(lastItemIndex);
		stack.push(lastItem);
	}
	
	private static boolean hasAtMostOneItemOnStack(Stack<Integer> stack) {
		return (stack.size() < 2);
	}
	
	private void popFromPushTo(Stack<Integer> from, Stack<Integer> to) {
		if (from.size() == 0) return;
		to.push(from.pop());
	}
}