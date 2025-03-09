package com.freimanvs.javapracticum.services;

import com.freimanvs.javapracticum.dto.User;
import com.freimanvs.javapracticum.exceptions.FinanceTrackerException;

public interface CommonService<ID, T> {
    T create(T user);

    T getBy(ID id);

    T updateBy(ID id, T t);

    ID deleteBy(ID id);
}
