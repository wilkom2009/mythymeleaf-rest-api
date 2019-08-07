package com.wilkom.caurisapp.common.service;

import java.util.List;

import com.wilkom.caurisapp.common.entity.Bank;
import com.wilkom.caurisapp.core.IMyService;

public interface BankService extends IMyService<Bank, Integer> {
	Bank findByName(String name);
}
