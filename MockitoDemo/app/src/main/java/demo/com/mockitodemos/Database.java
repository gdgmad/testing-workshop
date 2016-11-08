package demo.com.mockitodemos;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final List<String> ACCOUNTS = new ArrayList<>();

    static {
        if (ACCOUNTS.size() == 0) {
            ACCOUNTS.add("vipshah@microsoft.com");
            ACCOUNTS.add("vipulshah2010@gmail.com");
            ACCOUNTS.add("vipul@edureka.co");
        }
    }

    public boolean isAccountExist(String account) {
        return ACCOUNTS.contains(account);
    }
}
