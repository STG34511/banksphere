package com.banksphere.transfer.service.impl;

import com.banksphere.transfer.dto.request.TransferRequest;
import com.banksphere.transfer.dto.response.TransferResponse;
import com.banksphere.transfer.entity.enums.TransferMode;
import com.banksphere.transfer.service.TransferWorkflowService;
import com.banksphere.transfer.service.strategy.PaymentRailStrategy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TransferWorkflowServiceImpl implements TransferWorkflowService {

    private final Map<TransferMode, PaymentRailStrategy> strategyMap;

    public TransferWorkflowServiceImpl(List<PaymentRailStrategy> strategies) {

        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        PaymentRailStrategy::getSupportedMode,
                        Function.identity()
                ));
    }


    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {
        PaymentRailStrategy strategy = strategyMap.get(transferRequest.transferMode());
        if (strategy == null) {
            throw new IllegalArgumentException("Invalid transfer mode");
        }

        return strategy.process(transferRequest);
    }

}
