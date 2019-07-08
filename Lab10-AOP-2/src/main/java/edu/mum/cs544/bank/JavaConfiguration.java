package edu.mum.cs544.bank;

import edu.mum.cs544.bank.dao.AccountDAO;
import edu.mum.cs544.bank.dao.IAccountDAO;
import edu.mum.cs544.bank.jms.IJMSSender;
import edu.mum.cs544.bank.jms.JMSSender;
import edu.mum.cs544.bank.logging.ILogger;
import edu.mum.cs544.bank.logging.Logger;
import edu.mum.cs544.bank.service.CurrencyConverter;
import edu.mum.cs544.bank.service.ICurrencyConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("edu.mum.cs544.bank")
@EnableAspectJAutoProxy
public class JavaConfiguration {
    @Bean(name = "accountDAO")
    public IAccountDAO accountDAO() {
        return new AccountDAO();
    }

    @Bean(name = "currencyConverter")
    public ICurrencyConverter currencyConverter() {
        return new CurrencyConverter();
    }

    @Bean(name = "jmsSender")
    public IJMSSender jmsSender() {
        return new JMSSender();
    }

    @Bean(name = "logger")
    public ILogger logger() {
        return new Logger();
    }
}
