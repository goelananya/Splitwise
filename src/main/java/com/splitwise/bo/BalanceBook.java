package com.splitwise.bo;

import com.splitwise.service.BalanceService;
import com.splitwise.util.SplitWiseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Entity
public class BalanceBook {
    @Id
    private String balanceBookId;
    private String balanceIdList;

    private static final Logger logger = LoggerFactory.getLogger(BalanceService.class);

    protected BalanceBook() {
    }

    public BalanceBook(String bookId) {
        this.balanceBookId = bookId;
        this.balanceIdList = "";
    }

    public List<String> getBalanceIdList() {
        if (balanceIdList.equals("") || balanceIdList == null) {
            logger.info("Balance book is empty for:".concat(balanceBookId));
        } else {
            logger.info(this.balanceIdList.concat("BalanceId list for:").concat(this.balanceBookId));
        }
        return Arrays.asList(balanceIdList.split(","));
    }

    public void addBalanceId(String balanceId) {
        logger.info(balanceId.concat(":balance adding to balancebook:").concat(this.balanceBookId));
        if (balanceIdList.length() == 0) balanceIdList = balanceId;
        else this.balanceIdList = this.balanceIdList.concat(SplitWiseConstants.SEPARATOR).concat(balanceId);
        logger.info(balanceId.concat(":balance added to balancebook:").concat(this.balanceBookId));
        logger.info("Updated balance book:".concat(this.toString()));
    }

    @Override
    public String toString() {
        return "BalanceBook{" +
                "balanceBookId='" + balanceBookId + '\'' +
                ", balanceIdList='" + balanceIdList + '\'' +
                '}';
    }
}