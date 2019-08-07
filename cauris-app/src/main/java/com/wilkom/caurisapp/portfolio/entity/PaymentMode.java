package com.wilkom.caurisapp.portfolio.entity;

/**
 * To change this template, choose Tools | Templates
*/
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wilkom.caurisapp.common.entity.Bank;

/**
 *
 * @author Wilson <wilkom2009@yahoo.fr>
 */
@Embeddable
public class PaymentMode implements Serializable {

	public enum PaymentModeEnum {
		ESPECE, FLOOZ, TMONEY, CHEQUE, VIREMENT
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Enumerated
	private PaymentModeEnum mode;
	private String numero;
	@ManyToOne
	private Bank bank;

	public PaymentMode(PaymentModeEnum mode, String numero, Bank bank) {
		super();
		this.mode = mode;
		this.numero = numero;
		this.bank = bank;
	}

	public PaymentMode() {
		super();
		this.mode = PaymentModeEnum.ESPECE;
	}

	public PaymentModeEnum getMode() {
		return mode;
	}

	public void setMode(PaymentModeEnum mode) {
		this.mode = mode;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bank, mode, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentMode other = (PaymentMode) obj;
		return Objects.equals(bank, other.bank) && mode == other.mode && Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		return "PaymentMode [mode=" + mode + ", numero=" + numero + ", bank=" + bank + "]";
	}

}
