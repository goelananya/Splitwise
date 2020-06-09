package com.splitwise.bo;

import com.splitwise.service.BalanceService;
import com.splitwise.util.SplitWiseConstants;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@ToString
public class BalanceBook {
    @Id
    @GeneratedValue
    private Long balanceBookId;
    private String balanceIdList;

    private static final Logger logger = LoggerFactory.getLogger(BalanceService.class);

    public BalanceBook() {
        this.balanceIdList = "";
    }

    public List<String> getBalanceIdList() {
        logger.info(this.balanceIdList.concat("BalanceId list for:" + this.balanceBookId));
        if (balanceIdList.equals("")) return null;
        else return Arrays.asList(balanceIdList.split(","));
    }

    public void addBalanceId(Long balanceId) {
        logger.info(balanceId + ":balance adding to balancebook:" + this.balanceBookId);
        if (balanceIdList.length() == 0) balanceIdList = Long.toString(balanceId);
        else
            this.balanceIdList = this.balanceIdList.concat(SplitWiseConstants.SEPARATOR).concat(Long.toString(balanceId));
        logger.info(balanceId + ":balance added to balancebook:" + this.balanceBookId);
        logger.info("Updated balance book:".concat(this.toString()));
    }
}