package com.blockchainapp.Blockchain.Repository;

import com.blockchainapp.Blockchain.Models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {

    @Query(value = "SELECT * FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    List<Accounts> getUserAccountsById(@Param("user_id") int user_id);

    @Query(value = "SELECT sum(balance) FROM accounts WHERE user_id = :user_id", nativeQuery = true)
    BigDecimal getTotalBalance(@Param("user_id") int user_id);

    @Modifying
    @Query(value = "INSERT INTO accounts(user_id, account_number, account_name, account_type) VALUES" +
            "(:user_id, :account_number, :account_name, :account_type)", nativeQuery = true)
    @Transactional
    void createBankAccount(@Param("user_id") int user_id,
                           @Param("account_number") String account_number,
                           @Param("account_name") String account_name,
                           @Param("account_type") String account_type);

    @Query(value = "SELECT balance FROM accounts WHERE user_id = :user_id AND account_id = :account_id", nativeQuery = true)
    double UserAccountBalance(@Param("user_id") int user_id,
                             @Param("account_id") int account_id);

    @Query(value = "SELECT balance FROM accounts WHERE account_number = :account_number", nativeQuery = true)
    double getAccountBalance(@Param("account_number") String account_number);

    @Query(value = "UPDATE accounts set balance = :new_balance WHERE account_id = :account_id", nativeQuery = true)
    @Transactional
    @Modifying
    void NewAccountBalance(@Param("new_balance") double deduct_balance,
                              @Param("account_id") int account_id);

    @Query(value = "UPDATE accounts set balance = :new_balance WHERE account_number = :account_number", nativeQuery = true)
    @Transactional
    @Modifying
    void ChangeAccountBalance(@Param("new_balance") double new_balance,
                              @Param("account_number") String account_number);


    @Query(value = "SELECT * FROM accounts WHERE account_number = :account_number", nativeQuery = true)
    List<Accounts> getAccountName(@Param("account_number") String account_number);


    @Query(value = "UPDATE accounts set balance = balance + :depositAmount WHERE account_id = :account_id", nativeQuery = true)
    @Transactional
    @Modifying
    void ModifyAccountBalance(@Param("depositAmount") double deposit,
                           @Param("account_id") int account_id);

}
