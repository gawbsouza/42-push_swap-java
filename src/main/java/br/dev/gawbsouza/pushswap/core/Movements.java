package br.dev.gawbsouza.pushswap.core;

/**
 * Enum of movements that can be executed by {@code PushSwap}
 */
public enum Movements {

	SA, SB, SS,
	PA, PB,
	RA, RB, RR,
	RRA, RRB, RRR;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
}
