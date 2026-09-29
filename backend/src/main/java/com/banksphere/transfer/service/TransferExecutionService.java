package com.banksphere.transfer.service;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class TransferExecutionService {

    public <T> T executeWithRetry(Supplier<T> supplier) {

        for (int attempt = 1; ; attempt++) {

            try {
                return supplier.get();
            } catch (ObjectOptimisticLockingFailureException ex) {

                if (attempt == 3) {
                    throw ex;
                }

                System.out.println("Retrying transfer due to optimistic lock. Attempt :" + attempt);
            }
        }

    }
}
