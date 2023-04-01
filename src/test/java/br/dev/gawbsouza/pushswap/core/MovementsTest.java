package br.dev.gawbsouza.pushswap.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MovementsTest {

	@Test
	public void should_return_enum_string_as_lower_case() {
		
		assertEquals("pa", Movements.PA.toString());
		assertEquals("pb", Movements.PB.toString());
		assertEquals("sa", Movements.SA.toString());
		assertEquals("sb", Movements.SB.toString());
		assertEquals("ss", Movements.SS.toString());
		assertEquals("ra", Movements.RA.toString());
		assertEquals("rb", Movements.RB.toString());
		assertEquals("rr", Movements.RR.toString());
		assertEquals("rra", Movements.RRA.toString());
		assertEquals("rrb", Movements.RRB.toString());
		assertEquals("rrr", Movements.RRR.toString());
	}
	
}
