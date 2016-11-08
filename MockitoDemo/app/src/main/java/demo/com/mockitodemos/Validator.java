package demo.com.mockitodemos;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    public final Database database;
    private List<String> mAccounts;

    public Validator(Database database) {
        this.database = database;
        mAccounts = new ArrayList<>();
    }

    public void testAccount(String account) throws IllegalArgumentException {
        if (TextUtils.isEmpty(account)) {
            return;
        }
        if (database.isAccountExist(account)) {
            throw new IllegalArgumentException("Account Already Exist!");
        }
        mAccounts.add(account);
    }

    public List<String> getAccounts() {
        return mAccounts;
    }
}
