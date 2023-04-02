package br.dev.gawbsouza.pushswap.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PushSwapTest {
	
	@Nested
	public class ConstructorTest {
		
		@Test
		public void should_construct_with_no_input_numbers() {
			var ps = new PushSwap();
			assertNotNull(ps);
		}
		
		@Test
		public void should_construct_with_some_input_numbers() {
			var ps = new PushSwap(4, 5, 6);
			assertNotNull(ps);
		}
		
		@Test
		public void should_stacka_be_populated_and_stackb_be_empty() {
			
			var ps = new PushSwap(4, 5, 6);
			
			assertEquals(3, ps.getStackA().size());
			assertEquals(0, ps.getStackB().size());
		}
		
		@Test
		public void should_input_numbers_be_pushed_in_reverse_order() {
			
			var ps = new PushSwap(4, 5, 6);
			var stackA = ps.getStackA();
			
			assertEquals(4, stackA.pop());
			assertEquals(5, stackA.pop());
			assertEquals(6, stackA.pop());
		}
		
		@Test
		public void should_movements_list_be_empty() {
			var ps = new PushSwap(4, 5, 6);
			assertEquals(0, ps.getMovements().size());
			
		}
	}
	
	@Nested
	public class SAMovementTest {
		
		@Test
		public void should_execute_sa_with_empty_stacka() {
			
			var ps = new PushSwap();
			ps.sa();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SA, movements.get(0));
		}
		
		@Test
		public void should_execute_sa_with_one_item_on_stacka() {
			
			var ps = new PushSwap(1);
			ps.sa();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SA, movements.get(0));
		}
		
		@Test
		public void should_execute_sa_with_two_items_on_stacka() {
			
			var ps = new PushSwap(1, 2);
			ps.sa();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SA, movements.get(0));
			
			var stackA = ps.getStackA();
			
			assertEquals(2, stackA.pop());
			assertEquals(1, stackA.pop());
		}
		
		@Test
		public void should_execute_sa_with_three_items_on_stacka() {
			
			var ps = new PushSwap(1, 2, 3);
			ps.sa();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SA, movements.get(0));
			
			var stackA = ps.getStackA();
			
			assertEquals(2, stackA.pop());
			assertEquals(1, stackA.pop());
			assertEquals(3, stackA.pop());
		}
	}
	
	@Nested
	public class RAMovementTest {
		
		@Test
		public void should_execute_ra_with_empty_stacka() {
			
			var ps = new PushSwap();
			ps.ra();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RA, movements.get(0));
		}
		
		@Test
		public void should_execute_ra_with_one_item_on_stacka() {
			
			var ps = new PushSwap(5);
			ps.ra();
			var movements = ps.getMovements();
			
			assertEquals(1, ps.getStackA().size());
			assertEquals(1, movements.size());
			assertEquals(Movements.RA, movements.get(0));
		}
		
		@Test
		public void should_execute_ra_with_two_items_on_stacka() {
			
			var ps = new PushSwap(4, 5);
			ps.ra();
			var stackA = ps.getStackA();
			var movements = ps.getMovements();
			
			assertEquals(2, stackA.size());
			assertEquals(5, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(1, movements.size());
			assertEquals(Movements.RA, movements.get(0));
		}
		
		@Test
		public void should_execute_with_ra_three_items_on_stacka() {
			
			var ps = new PushSwap(4, 5, 6);
			ps.ra();
			var stackA = ps.getStackA();
			var movements = ps.getMovements();
			
			assertEquals(3, stackA.size());
			assertEquals(5, stackA.pop());
			assertEquals(6, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(1, movements.size());
			assertEquals(Movements.RA, movements.get(0));
		}

	}
	
	@Nested
	public class RRAMovementTest {
		
		@Test
		public void should_execute_rra_with_empty_stacka() {
			
			var ps = new PushSwap();
			ps.rra();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RRA, movements.get(0));
		}
		
		@Test
		public void should_execute_rra_with_two_items_on_stacka() {
			
			var ps = new PushSwap(4, 5);
			ps.rra();
			var stackA = ps.getStackA();
			var movements = ps.getMovements();
			
			assertEquals(2, stackA.size());
			assertEquals(5, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(1, movements.size());
			assertEquals(Movements.RRA, movements.get(0));
		}
		
		@Test
		public void should_execute_rra_with_three_items_on_stacka() {
			var ps = new PushSwap(4, 5, 6);
			ps.rra();
			var stackA = ps.getStackA();
			var movements = ps.getMovements();
			
			assertEquals(3, stackA.size());
			assertEquals(6, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(5, stackA.pop());
			assertEquals(1, movements.size());
			assertEquals(Movements.RRA, movements.get(0));
		}
	}
	
	@Nested
	public class PAMovimentTest {
		
		private Field stackBField;
		
		@BeforeEach
		public void setUp() throws NoSuchFieldException, SecurityException {
			stackBField = PushSwap.class.getDeclaredField("b");
			stackBField.setAccessible(true);
		}
		
		@Test
		public void should_execute_pa_with_empty_stacka_and_stackb() {
			
			var ps = new PushSwap();
			ps.pa();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.PA, movements.get(0));
		}
		
		@Test
		public void should_execute_pa_with_empty_stacka_and_one_item_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException  {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(4);
			ps.pa();
			
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.PA, movements.get(0));
			assertEquals(1, stackA.size());
			assertEquals(4, stackA.pop());
			assertEquals(0, ps.getStackB().size());
		}
		
		@Test
		public void should_execute_pa_with_empty_stacka_and_two_items_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException  {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(4);
			ps.pa();
			
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.PA, movements.get(0));
			assertEquals(1, stackA.size());
			assertEquals(4, stackA.pop());
			assertEquals(0, ps.getStackB().size());
		}
		
		@Test
		public void should_execute_pa_with_one_item_on_stacka_and_one_item_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException  {
			
			var ps = new PushSwap(5);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(4);
			ps.pa();
			
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.PA, movements.get(0));
			assertEquals(2, stackA.size());
			assertEquals(4, stackA.pop());
			assertEquals(5, stackA.pop());
			assertEquals(0, ps.getStackB().size());
		}
	}
	
	@Nested
	public class SBMovementTest {
		
		private Field stackBField;
		
		@BeforeEach
		public void setUp() throws NoSuchFieldException, SecurityException {
			stackBField = PushSwap.class.getDeclaredField("b");
			stackBField.setAccessible(true);
		}		
		
		@Test
		public void should_execute_sb_with_empty_stackb() {
			
			var ps = new PushSwap();
			ps.sb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SB, movements.get(0));
		}
		
		@Test
		public void should_execute_sb_with_one_item_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(1);
			ps.sb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SB, movements.get(0));
			assertEquals(1, stackB.pop());
		}
		
		@Test
		public void should_execute_sb_with_two_items_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(2);
			stackB.push(1);
			ps.sb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SB, movements.get(0));
			assertEquals(2, stackB.pop());
			assertEquals(1, stackB.pop());
		}
		
		@Test
		public void should_execute_sb_with_three_items_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(3);
			stackB.push(2);
			stackB.push(1);
			ps.sb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SB, movements.get(0));
			assertEquals(2, stackB.pop());
			assertEquals(1, stackB.pop());
			assertEquals(3, stackB.pop());
		}
	}
	
	@Nested
	public class RBMovementTest {
		
		private Field stackBField;
		
		@BeforeEach
		public void setUp() throws NoSuchFieldException, SecurityException {
			stackBField = PushSwap.class.getDeclaredField("b");
			stackBField.setAccessible(true);
		}	
		
		@Test
		public void should_execute_rb_with_empty_stackb() {
			
			var ps = new PushSwap();
			ps.rb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RB, movements.get(0));
		}
		
		@Test
		public void should_execute_rb_with_one_item_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(5);
			ps.rb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RB, movements.get(0));
			assertEquals(5, stackB.pop());
		}
		
		@Test
		public void should_execute_rb_with_two_items_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(5);
			stackB.push(4);
			ps.rb();
			var movements = ps.getMovements();

			assertEquals(1, movements.size());
			assertEquals(Movements.RB, movements.get(0));
			assertEquals(5, stackB.pop());
			assertEquals(4, stackB.pop());
		}
		
		@Test
		public void should_execute_with_rb_three_items_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(6);
			stackB.push(5);
			stackB.push(4);
			ps.rb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RB, movements.get(0));
			assertEquals(5, stackB.pop());
			assertEquals(6, stackB.pop());
			assertEquals(4, stackB.pop());
		}

	}
	
	@Nested
	public class RRBMovementTest {
		
		private Field stackBField;
		
		@BeforeEach
		public void setUp() throws NoSuchFieldException, SecurityException {
			stackBField = PushSwap.class.getDeclaredField("b");
			stackBField.setAccessible(true);
		}	
		
		@Test
		public void should_execute_rrb_with_empty_stackb() {
			
			var ps = new PushSwap();
			ps.rrb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RRB, movements.get(0));
		}
		
		@Test
		public void should_execute_rrb_with_two_items_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(5);
			stackB.push(4);
			ps.rrb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RRB, movements.get(0));
			assertEquals(5, stackB.pop());
			assertEquals(4, stackB.pop());
		}
		
		@Test
		public void should_execute_rrb_with_three_items_on_stackb() 
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap();
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(6);
			stackB.push(5);
			stackB.push(4);
			ps.rrb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RRB, movements.get(0));
			assertEquals(6, stackB.pop());
			assertEquals(4, stackB.pop());
			assertEquals(5, stackB.pop());
		}
	}
	
	@Nested
	public class PBMovimentTest {
		
		@Test
		public void should_execute_pb_with_empty_stacka_and_stackb() {
			
			var ps = new PushSwap();
			ps.pb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.PB, movements.get(0));
		}
		
		@Test
		public void should_execute_pb_with_empty_stackb_and_one_item_on_stacka() {
			
			var ps = new PushSwap(4);
			ps.pb();
			var movements = ps.getMovements();
			var stackB = ps.getStackB();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.PB, movements.get(0));
			assertEquals(0, ps.getStackA().size());
			assertEquals(1, stackB.size());
			assertEquals(4, stackB.pop());
		}
		
		@Test
		public void should_execute_pb_with_empty_stackb_and_two_items_on_stacka() {
			
			var ps = new PushSwap(4, 5);
			ps.pb();
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			var stackB = ps.getStackB();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.PB, movements.get(0));
			assertEquals(1, stackA.size());
			assertEquals(5, stackA.pop());
			assertEquals(1, stackB.size());
			assertEquals(4, stackB.pop());
		}
		
		@Test
		public void should_execute_pb_with_one_item_on_stackb_and_one_item_on_stacka() 
				throws	IllegalArgumentException, IllegalAccessException, 
						NoSuchFieldException, SecurityException  {
			
			var ps = new PushSwap(5);
			
			Field stackBField = PushSwap.class.getDeclaredField("b");
			stackBField.setAccessible(true);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(4);
			ps.pb();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.PB, movements.get(0));
			assertEquals(2, stackB.size());
			assertEquals(5, stackB.pop());
			assertEquals(4, stackB.pop());
			assertEquals(0, ps.getStackA().size());
		}
	}
	
	//SS
	@Nested
	public class SSMovimentTest {
		
		private Field stackBField;
		
		@BeforeEach
		public void setUp() throws NoSuchFieldException, SecurityException {
			stackBField = PushSwap.class.getDeclaredField("b");
			stackBField.setAccessible(true);
		}	
		
		@Test
		public void should_execute_ss_with_empty_stacka_and_stackb() {
			
			var ps = new PushSwap();
			ps.ss();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SS, movements.get(0));
		}
		
		@Test
		public void should_execute_ss_with_one_item_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(5);
			ps.ss();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SS, movements.get(0));
			assertEquals(4, ps.getStackA().pop());
			assertEquals(5, stackB.pop());
		}
	
		@Test
		public void should_execute_ss_with_two_items_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4, 5);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(7);
			stackB.push(6);
			ps.ss();
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SS, movements.get(0));
			assertEquals(5, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(7, stackB.pop());
			assertEquals(6, stackB.pop());
		}
		
		@Test
		public void should_execute_ss_with_three_items_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4, 5, 6);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(8);
			stackB.push(7);
			stackB.push(6);
			ps.ss();
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.SS, movements.get(0));
			assertEquals(5, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(6, stackA.pop());
			assertEquals(7, stackB.pop());
			assertEquals(6, stackB.pop());
			assertEquals(8, stackB.pop());
		}
		
	}
	

	@Nested
	public class RRMovimentTest {
		
		private Field stackBField;
		
		@BeforeEach
		public void setUp() throws NoSuchFieldException, SecurityException {
			stackBField = PushSwap.class.getDeclaredField("b");
			stackBField.setAccessible(true);
		}	
		
		@Test
		public void should_execute_rr_with_empty_stacka_and_stackb() {
			
			var ps = new PushSwap();
			ps.rr();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RR, movements.get(0));
		}
		
		@Test
		public void should_execute_rr_with_one_item_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(5);
			ps.rr();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RR, movements.get(0));
			assertEquals(4, ps.getStackA().pop());
			assertEquals(5, stackB.pop());
		}

		@Test
		public void should_execute_rr_with_two_items_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4, 5);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(7);
			stackB.push(6);
			ps.rr();
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RR, movements.get(0));
			assertEquals(5, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(7, stackB.pop());
			assertEquals(6, stackB.pop());
		}
		
		@Test
		public void should_execute_rr_with_three_items_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4, 5, 6);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(8);
			stackB.push(7);
			stackB.push(6);
			ps.rr();
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RR, movements.get(0));
			assertEquals(5, stackA.pop());
			assertEquals(6, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(7, stackB.pop());
			assertEquals(8, stackB.pop());
			assertEquals(6, stackB.pop());
		}
	}
	
	@Nested
	public class RRRMovimentTest {
		
		private Field stackBField;
		
		@BeforeEach
		public void setUp() throws NoSuchFieldException, SecurityException {
			stackBField = PushSwap.class.getDeclaredField("b");
			stackBField.setAccessible(true);
		}	
		
		@Test
		public void should_execute_rrr_with_empty_stacka_and_stackb() {
			
			var ps = new PushSwap();
			ps.rrr();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RRR, movements.get(0));
		}
		
		@Test
		public void should_execute_rrr_with_one_item_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(5);
			ps.rrr();
			var movements = ps.getMovements();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RRR, movements.get(0));
			assertEquals(4, ps.getStackA().pop());
			assertEquals(5, stackB.pop());
		}

		@Test
		public void should_execute_rrr_with_two_items_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4, 5);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(7);
			stackB.push(6);
			ps.rrr();
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RRR, movements.get(0));
			assertEquals(5, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(7, stackB.pop());
			assertEquals(6, stackB.pop());
		}
		
		@Test
		public void should_execute_rrr_with_three_items_on_stacka_and_stackb()
				throws IllegalArgumentException, IllegalAccessException {
			
			var ps = new PushSwap(4, 5, 6);
			
			@SuppressWarnings("unchecked")
			Stack<Integer> stackB = (Stack<Integer>) stackBField.get(ps);
			
			stackB.push(8);
			stackB.push(7);
			stackB.push(6);
			ps.rrr();
			var movements = ps.getMovements();
			var stackA = ps.getStackA();
			
			assertEquals(1, movements.size());
			assertEquals(Movements.RRR, movements.get(0));
			assertEquals(6, stackA.pop());
			assertEquals(4, stackA.pop());
			assertEquals(5, stackA.pop());
			assertEquals(8, stackB.pop());
			assertEquals(6, stackB.pop());
			assertEquals(7, stackB.pop());
		}
	}
	
	@Nested
	public class IsStackOrdenedTest {
		
		@Test
		public void shoul_return_true_when_stack_is_empty() {
			assertTrue(PushSwap.isStackOrdered(new Stack<Integer>()));
		}
		
		@Test
		public void shoul_return_true_when_stack_has_only_one_item() {
			
			var stack = new Stack<Integer>();
			stack.push(42);
			
			assertTrue(PushSwap.isStackOrdered(stack));
		}
		
		@Test
		public void shoul_return_false_when_stack_is_not_ordered() {
			
			var stackWithTwoItems = new Stack<Integer>();
			stackWithTwoItems.push(3);
			stackWithTwoItems.push(42);
			
			var stackWithThreeItems = new Stack<Integer>();
			stackWithThreeItems.push(3);
			stackWithThreeItems.push(42);
			stackWithThreeItems.push(5);
			
			assertFalse(PushSwap.isStackOrdered(stackWithTwoItems));
			assertFalse(PushSwap.isStackOrdered(stackWithThreeItems));
		}
		
		@Test
		public void shoul_return_true_when_stack_is_ordered() {
			
			var stackWithTwoItems = new Stack<Integer>();
			stackWithTwoItems.push(42);
			stackWithTwoItems.push(3);
			
			var stackWithThreeItems = new Stack<Integer>();
			stackWithThreeItems.push(42);
			stackWithThreeItems.push(5);
			stackWithThreeItems.push(3);
			
			assertTrue(PushSwap.isStackOrdered(stackWithTwoItems));
			assertTrue(PushSwap.isStackOrdered(stackWithThreeItems));
		}
		
	}
	
}
