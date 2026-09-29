package com.banksphere.payment.service.neft;

import com.banksphere.transfer.entity.Transfer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NEFTScheduler {

    private final NEFTProcessingService neftProcessingService;

    @Scheduled(fixedDelay = 10 * 1000)
    public void run() {
        List<Transfer> transfers = neftProcessingService.pickTransfer();
        neftProcessingService.process(transfers);
    }

}
