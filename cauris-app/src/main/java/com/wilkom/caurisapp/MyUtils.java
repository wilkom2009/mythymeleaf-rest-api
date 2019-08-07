package com.wilkom.caurisapp;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//
//import org.apache.shiro.crypto.RandomNumberGenerator;
//import org.apache.shiro.crypto.SecureRandomNumberGenerator;
//import org.apache.shiro.crypto.hash.Sha256Hash;


public class MyUtils {
	public static int DAYS_OF_MONTH = 30;// nombre de jours du mois selon notre règle

	/**
	 * 
	 * @param init
	 * @param nb
	 * @return un code init+3 chiffres
	 */
	public static String generate1000Code(String init, Integer nb) {
		if (nb < 10) {
			return init.concat("00" + nb);
		} else if (nb < 100) {
			return init.concat("0" + nb);
		} else {
			return init.concat("" + nb);
		}
	}

	/**
	 * 
	 * @param init
	 * @param nb
	 * @return un code init+5 chiffres
	 */
	public static String generate100000Code(String init, Integer nb) {
		if (nb < 10) {
			return init.concat("0000" + nb);
		} else if (nb < 100) {
			return init.concat("000" + nb);
		} else if (nb < 1000) {
			return init.concat("00" + nb);
		} else if (nb < 10000) {
			return init.concat("0" + nb);
		} else {
			return init.concat("" + nb);
		}
	}


	/**
	 * Is Date A Business Day?
	 * 
	 * @param cal
	 * @return boolean
	 */
	public static boolean isBusinessDay(Date date) {
		if (date == null)
			return false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// check if weekend
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return false;
		}

		// check if New Year's Day
		if (cal.get(Calendar.MONTH) == Calendar.JANUARY && cal.get(Calendar.DAY_OF_MONTH) == 1) {
			return false;
		}

		// check if Christmas
		if (cal.get(Calendar.MONTH) == Calendar.DECEMBER && cal.get(Calendar.DAY_OF_MONTH) == 25) {
			return false;
		}

		// check if labor day 1stMay
		if (cal.get(Calendar.MONTH) == Calendar.MAY && cal.get(Calendar.DAY_OF_MONTH) == 1) {
			return false;
		}

		// check if 4th of July
//		if (cal.get(Calendar.MONTH) == Calendar.JULY
//			&& cal.get(Calendar.DAY_OF_MONTH) == 4) {
//			return false;
//		}
//		
//		// check Thanksgiving (4th Thursday of November)
//		if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
//			&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 4
//			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
//			return false;
//		}
//		
//		// check Memorial Day (last Monday of May)
//		if (cal.get(Calendar.MONTH) == Calendar.MAY
//			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY
//			&& cal.get(Calendar.DAY_OF_MONTH) > (31 - 7) ) {
//			return false;
//		}
//		
//		// check Labor Day (1st Monday of September)
//		if (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER
//			&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 1
//			&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
//			return false;
//		}
//		
//		// check President's Day (3rd Monday of February)
//		if (cal.get(Calendar.MONTH) == Calendar.FEBRUARY
//		&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
//		&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
//		return true;
//		}
//		
//		// check Veterans Day (November 11)
//		if (cal.get(Calendar.MONTH) == Calendar.NOVEMBER
//		&& cal.get(Calendar.DAY_OF_MONTH) == 11) {
//		return true;
//		}
//		
//		// check MLK Day (3rd Monday of January)
//		if (cal.get(Calendar.MONTH) == Calendar.JANUARY
//		&& cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == 3
//		&& cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
//		return true;
//		}

		// IF NOTHING ELSE, IT'S A BUSINESS DAY
		return true;
	}

	public static Date getFirstDayOfMonth(Date d) {
		if (d == null)
			return null;
		d = MyUtils.convertToDateViaInstant(MyUtils.convertToLocalDateTimeViaInstant(d));
		System.out.println("xxxxxxx : " + d);
		return convertToDateViaInstant(
				LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()).withDayOfMonth(1));
	}

	/**
	 * Determine le dernier jour du mois de la date passée en parametre
	 * 
	 * @param d
	 * @return
	 */
	public static Date getLastDayOfMonth(Date d) {
		if (d == null)
			return null;
		d = MyUtils.convertToDateViaInstant(MyUtils.convertToLocalDateTimeViaInstant(d));
		return convertToDateViaInstant(LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault())
				.with(TemporalAdjusters.lastDayOfMonth()));
	}

	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		if (dateToConvert == null)
			return null;
		java.sql.Date sqlDate = new java.sql.Date(dateToConvert.getTime());
		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
		return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		if (dateToConvert == null)
			return null;
		System.out.println("D : " + dateToConvert);
		java.sql.Date sqlDate = new java.sql.Date(dateToConvert.getTime());
		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
		System.out.println("DS : " + utilDate);
		return utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date convertToDateViaInstant(LocalDate dateToConvert) {
		if (dateToConvert == null)
			return null;
		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		if (dateToConvert == null)
			return null;
		return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date getNextWorkDay(Date date) {
		if (date == null)
			return null;
		LocalDateTime signLDT = MyUtils.convertToLocalDateTimeViaInstant(date);
		LocalDateTime effLDT = signLDT;
		// la date effet est date de signature+1jr
		// la date effet doit etre un jour ouvrable
		if (signLDT.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
			effLDT = signLDT.plusDays(3);
		} else if (signLDT.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			effLDT = signLDT.plusDays(2);
		} else {
			effLDT = signLDT.plusDays(1);
		}
		return MyUtils.convertToDateViaInstant(effLDT);
	}

	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return le nombre de jours entre deux dates, si date2 < date1 ==> nb=0
	 */
	public static int getDayBetween2(Date date1, Date date2) {
		// je recupere la date la plus petite
		Date dtMin = date1.before(date2) ? date1 : date2;
		// et la date la plus grande;
		Date dtMax = date1.after(date2) ? date1 : date2;
		int nbJrs = 0;
		// ensuite j'incremente la date min jusqu'a atteindre la date max
		while (!equal(dtMin, dtMax)) {
			nbJrs++;
			dtMin = getDateFromByDays(dtMin, 1);
		}
		return nbJrs < 0 ? 0 : nbJrs;
	}

	/**
	 * retourne la date apres days jours; si days<0, retourne une date anterieur de
	 * days jours tres pratik!!!
	 *
	 *
	 * 
	 * @param processDate
	 * @param days
	 * @return
	 */
	public static Date getDateFromByDays(Date processDate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(processDate);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		Date resultDate = calendar.getTime();
		return resultDate;
	}

	// verifie si l'egalite entre deux dates (au jour pres)
	public static boolean equal(Date dt1, Date dt2) {
		return new SimpleDateFormat("dd/MM/yyyy").format(dt1).equals(new SimpleDateFormat("dd/MM/yyyy").format(dt2));
	}

	/**
	 * Détermine le nombre de jours dans le mois issue de la date @d en paramètre
	 * 
	 * @param d
	 * @return
	 */
	public static Integer getDaysOfCurrentMonth(Date d) {
		int month = convertToLocalDateTimeViaInstant(d).getMonthValue();
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		} else if (month == 2) {
			return isLeapYear(convertToLocalDateTimeViaInstant(d).getYear()) ? 29 : 28;
		}
		return 0;
	}

	/**
	 * Détermine si année bissextile ou pas
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if (year % 4 == 0) {
			if (year % 100 == 0) {
				// year is divisible by 400, hence the year is a leap year
				if (year % 400 == 0)
					return true;
				else
					return false;
			} else
				return true;
		} else
			return false;
	}

	/**
	 * Détermine le nombre de jours restants dans le mois en cours de la date d
	 * 
	 * @param d
	 * @return
	 */
	public static Integer getRemainingMonthDays(Date d) {
		return getDayBetween2(d, getLastDayOfMonth(d));
	}

	/**
	 * 
	 * @param annualRate
	 * @return calcule le taux mensuel à partir du taux annuel
	 */
	public static double getMonthlyFromYearRate(double yearRate) {
		return yearRate / 1200;
	}

	/**
	 * 
	 * @param annualRate
	 * @return calcule le taux journalier à partir du taux annuel
	 */
	public static double getDailyFromYearRate(double annualRate) {
		return annualRate / 36500;
	}

	/**
	 * 
	 * @param capital
	 * @param rate    en %
	 * @param days
	 * @return calcule la commission par rapport aux jours à partir du taux annuel,
	 *         du capital et du nombre de jours
	 */
	public static double calculateDaysCommissionFromYearRate(double capital, double rate, int days) {
		return Math.round(capital * rate * days / 36000);// année de 360jrs/12 MOIS DE 30JS/MOIS
	}

	/**
	 * 
	 * @param capital
	 * @param rate    en %
	 * @param days
	 * @return calcule la commission par rapport aux jours à partir du taux mensuel,
	 *         du capital et du nombre de jours
	 */
	public static double calculateDaysCommissionFromMonthRate(double capital, double rate, int days) {
		return Math.round(capital * rate * days / 3000);// année de 30jrs/mois
	}

//	/**
//	 * Calcule l'intéret mensuel d'un contrat
//	 * 
//	 * @param c
//	 * @return
//	 */
//	public static double calculateMonthlyInterest(Contract c) {
//		System.out.println(c+" - "+c.getCapital() +" - "+ c.getInterestRate());
//		return Math.round(c.getCapital() * c.getInterestRate() / 1200);
//	}
//
//	/**
//	 * Calcule l interet annuel d'un contrat
//	 * 
//	 * @param c
//	 * @return
//	 */
//	public static double calculateYearInterest(Contract c) {
//		return Math.round(c.getCapital() * c.getInterestRate() / 100);
//	}
//
//	/**
//	 * Calcule la commission mensuelle d'un contrat
//	 * 
//	 * @param c
//	 * @return
//	 */
//	public static double calculateMonthlyCommercialCommission(Contract c) {
//		return Math.round(c.getCapital() * c.getCommercialRate() / (100 * c.getNbMonthComRate()));
//	}
//
//	/**
//	 * Calcule la commission globale d'un contrat
//	 * 
//	 * @param c
//	 * @return
//	 */
//	public static double calculateCommercialCommission(Contract c) {
//		return Math.round(c.getCapital() * c.getCommercialRate() / 100);
//	}
//
//	/**
//	 * calcul les frais de cloture ou de fermeture
//	 * 
//	 * @param c
//	 * @return
//	 */
//	public static double calculateWithdrawalFees(Contract c) {
//		return Math.round(c.getClosingRate() * c.getCapital() / 100);//
//	}
//
//	/**
//	 * Calcule de l'intérêt
//	 * 
//	 * @param c
//	 * @param stDte
//	 * @param endDte
//	 * @return l'intérêt au cours d'une période
//	 */
//	public static double calculateInterest(Contract c, Date stDte, Date endDte) {
//		if (c != null) {
//			return MyUtils.calculateDaysCommissionFromYearRate(c.getCapital(), c.getInterestRate(),
//					getDayBetween2(stDte, endDte));
//		}
//		return 0d;
//	}
//
//	public static double calculateCommission(Contract c, Date stDte, Date endDte) {
//		if (c != null) {
//			/**
//			 * la commission couvre seulement un temps à partir de la date de signature
//			 * alors ici on vérifie d'abord si la date fin de l'intervalle (endDte) est
//			 * avant ou après la date butoire (contract.dateEffect +
//			 * contract.nbMonthComRate*30jr) de la commission
//			 */
//			endDte = getCommissionPeriodEndDate(c, endDte);
//			return calculateDaysCommissionFromMonthRate(c.getCapital(), c.getCommercialRate(),
//					getDayBetween2(stDte, endDte));
//		}
//		return 0d;
//	}
//
//	/**
//	 * Détermine la date fin valide d'un intervalle de calcul de commission La
//	 * commission couvre seulement un temps à partir de la date de signature alors
//	 * ici on vérifie d'abord si la date fin de l'intervalle (endDte) est avant ou
//	 * après la date butoire (contract.dateEffect + contract.nbMonthComRate*30jr) de
//	 * la commission
//	 * 
//	 * @param c
//	 * @param endDte
//	 * @return la date fin intervalle pour une commission suivant la règle de
//	 *         gestion
//	 */
//	public static Date getCommissionPeriodEndDate(Contract c, Date endDte) {
//		if (c != null) {
//			return getDateFromByDays(c.getDateEffect(), c.getNbMonthComRate() * 30).after(endDte) ? endDte
//					: getDateFromByDays(c.getDateEffect(), c.getNbMonthComRate() * 30);
//		}
//		return null;
//	}
//
//	/**
//	 * Calcul du nombre de jours pour déterminer l'intéret avant un retrait
//	 * 
//	 * @param c
//	 * @param w
//	 * @return le nombre de jour pour intéret
//	 */
//	public static int getDaysForInterest(Contract c, WithdrawalOperation w) {
//		return 0;
////		return c.getDateEffectInterest().before(w.getDateEffect())
////				? getDayBetween2(c.getDateEffectInterest(), w.getDateEffect())
////				: 0;
//	}
//
//	/**
//	 * Générer les interets mensuels
//	 * 
//	 * @param contract
//	 * @param type
//	 * @param month
//	 * @return intéret
//	 */
//	public static CommissionOperation getMonthlyInterest(Contract contract, InterestType type, Month month,
//			Integer year) {
//		CommissionOperation co1 = new CommissionOperation();
////		co1.setContract(contract);
////		co1.setNbJours(DAYS_OF_MONTH);
////		co1.setTimeUnit(MyTimeUnit.MONTHS);
////		co1.setMonth(month);
////		co1.setYear(year);
//		if (type.equals(InterestType.CUSTOMER_INTEREST)) {
//			co1.setCredit((calculateMonthlyInterest(contract)));
//			co1.setType(InterestType.CUSTOMER_INTEREST);
//		} else if (type.equals(InterestType.COMMERCIAL_INTEREST)) {
//			co1.setCredit((calculateMonthlyCommercialCommission(contract)));
//			co1.setType(InterestType.COMMERCIAL_INTEREST);
//		}
//
//		return co1;
//	}
//
//	public static List<CommissionOperation> getMonthlyInterests(Contract contract, InterestType type, Month month,
//			Integer year) {
//		List<CommissionOperation> list = new ArrayList<>();
//		if (type.equals(InterestType.CUSTOMER_INTEREST)) {
//			for (Avenante av : contract.getAvenanteList()) {
////				if (av.getMonthlyInterest(month, year) != null) {
////					list.add(av.getMonthlyInterest(month, year));
////				}
//			}
//		}
//		System.out.println("Commissions : " + list);
//		return list;
//	}
//
//	/**
//	 * Crée une liste des interets/commissions de la période indiquée pour un
//	 * contrat donné
//	 * 
//	 * @param contract
//	 * @param type
//	 * @param start
//	 * @param end
//	 * @return
//	 */
//	public static List<CommissionOperation> generateMonthlyInterests(Contract contract, InterestType type, Date start,
//			Date end) {
//		List<CommissionOperation> list = new ArrayList<>();
//		if (type.equals(InterestType.CUSTOMER_INTEREST)) {
//			for (Avenante av : contract.getAvenanteList()) {
////				if (av.generatePeriodInterest(start, end) != null) {
////					list.add(av.generatePeriodInterest(start, end));
////				}
//			}
//		}
//		System.out.println("Commissions : " + list);
//		return list;
//	}
//
//	public static List<CommissionOperation> getMonthlyMultipleInterests(List<Contract> contractList, InterestType type,
//			Month month, Integer year) {
//		List<CommissionOperation> list = new ArrayList<>();
//		System.out.println("List : " + contractList);
//		for (Contract c : contractList) {
//			list.addAll(getMonthlyInterests(c, type, month, year));
//		}
//
//		return list;
//	}
//
//	/**
//	 * Crée une liste des interets/commissions de la période indiquée pour une liste
//	 * de contrats
//	 * 
//	 * @param contractList
//	 * @param type
//	 * @param start
//	 * @param end
//	 * @return
//	 */
//	public static List<CommissionOperation> generateMonthlyMultipleInterests(List<Contract> contractList,
//			InterestType type, Date start, Date end) {
//		List<CommissionOperation> list = new ArrayList<>();
//		System.out.println("List : " + contractList);
//		for (Contract c : contractList) {
//			list.addAll(generateMonthlyInterests(c, type, start, end));
//		}
//
//		return list;
//	}
//
//	public static Date getLastDayOfMonth(Month month, Integer year) {
//		return convertToDateViaInstant(LocalDateTime.of(year, month.getValue(), month.length(isLeapYear(year)), 0, 0));
//	}
//
//	public static Date getFirstDayOfMonth(Month month, Integer year) {
//		return convertToDateViaInstant(LocalDateTime.of(year, month.getValue(), 1, 0, 0));
//	}
//
//	public static Long getCountMonthsBeetwen(Month mStart, Integer yearStart, Month mEnd, Integer yearEnd) {
//		return ChronoUnit.MONTHS.between(LocalDateTime.of(yearStart, mStart.getValue(), 1, 0, 0),
//				LocalDateTime.of(yearEnd, mEnd.getValue(), mEnd.length(isLeapYear(yearEnd)), 0, 0)) + 1;
//	}
//
//	/**
//	 * Générer une liste de tuples (Month-Year) à partir d'une période donnée
//	 * 
//	 * @return
//	 */
//	public static List<MonthYearModel> getMonthsYearsFrom(Month mStart, Integer yearStart, Month mEnd,
//			Integer yearEnd) {
//		List<MonthYearModel> ls = new ArrayList<>();
//		for (int i = yearStart; i <= yearEnd; i++) {
//			if (i == yearStart && i != yearEnd) {
//				for (int j = mStart.getValue(); j <= 12; j++) {
//					ls.add(new MonthYearModel(Month.values()[j - 1], Year.of(i)));
//				}
//			} else if (i == yearStart && i == yearEnd) {
//				if (mStart.equals(mEnd)) {
//					ls.add(new MonthYearModel(mStart, Year.of(i)));
//					break;
//				} else {
//					for (int j = mStart.getValue(); j <= mEnd.getValue(); j++) {
//						ls.add(new MonthYearModel(Month.values()[j - 1], Year.of(i)));
//					}
//				}
//			} else if (i != yearStart && i == yearEnd) {
//				for (int j = 1; j <= mEnd.getValue(); j++) {
//					ls.add(new MonthYearModel(Month.values()[j - 1], Year.of(i)));
//				}
//			} else {
//				for (int j = 1; j <= 12; j++) {
//					ls.add(new MonthYearModel(Month.values()[j - 1], Year.of(i)));
//				}
//			}
//
//		}
//
//		return ls;
//	}
//
//	public static void main(String[] args) {
//		LocalDateTime ldt = LocalDateTime.now().minusDays(28).minusMonths(5).plusDays(9);
//		LocalDateTime ldtD = LocalDateTime.now().minusDays(28).minusMonths(0);
//		LocalDateTime ldtF = ldtD.plusDays(29);
//
//		Contract c = new Contract(null, null, convertToDateViaInstant(ldt), convertToDateViaInstant(ldt), null,
//				1200000d, 100d, 0d, 0d);
//		c.setCommercialRate(10d);
//		c.setNbMonthComRate(5);
//		Avenante av = Avenante.getStartingAvenante(c);
//
//		IPeriod p = new IPeriod(MyUtils.convertToDateViaInstant(ldtD), MyUtils.convertToDateViaInstant(ldtF), av, null,
//				true);
//		System.out.println("Debut contrat : " + c.getDateEffect());
//		System.out.println("Fin commission : " + c.getCommercialCommissionLastDay());
//		System.out.println("Avenant : " + av);
//		System.out.println("Periode : " + p.getStartDate() + " à " + p.getEndDate());
//		System.out.println("Interet : " + p.getMyInterest());
//		System.out.println("Commission : " + p.getMyCommission());
//
//		LocalDateTime jour = LocalDateTime.now().minusDays(28).plusMonths(3);
//		System.out.println("Debut mois : " + MyUtils.getFirstDayOfMonth(MyUtils.convertToDateViaInstant(jour)));
//		System.out.println("Fin mois : " + MyUtils.getLastDayOfMonth(MyUtils.convertToDateViaInstant(jour)));
//
////		System.out.println("Job start at :" + ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond() + " of "
////				+ldt.getDayOfMonth()+" "+ ldt.getMonth() + " - " + Year.now().getValue() + " AV / " + av);
////		CommissionOperation co = av.getInterest(convertToDateViaInstant(ldt),
////				convertToDateViaInstant(LocalDateTime.now()));
////		System.out.println("Com periodique : " + co+" "
////				+ "\nDebut : "+co.getDateDebut()+" - fin :"+co.getDateFin()+"\n"
////						+ "Nb jr"+co.getNbJours()+"\n"
////								+ "Credit : "+co.getCredit());
//
////		System.out.println(
////				"Com mensuel : " + av.getMonthlyInterest(ldt.getMonth(), Year.now().getValue()).getFormattedSolde());
//
////		System.out.println("Nb mois  : " + getCountMonthsBeetwen(Month.APRIL, 2019, Month.MAY, 2019));
////		System.out.println("List : " + getLastDayOfMonth(Month.APRIL, 2019));
////		System.out.println("Commission mensuel : " + calculateMonthlyCommercialCommission(c));
////		System.out.println("COmmission globale : " + MyUtils.getRemainingMonthDays(new Date()));
////
////		for (MonthYearModel mym : getMonthsYearsFrom(Month.JANUARY, 2019, Month.APRIL, 2019)) {
////			System.out.println("Moi-Année : " + mym);
////		}
//	}
}
