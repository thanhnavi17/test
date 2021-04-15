package com.ib.controller;

import com.ib.entity.*;
import com.ib.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OTPDao otpDao;

    @Autowired
    private TransactionDao transactionDao;

    @Autowired
    private TransactionEwalletDao teDao;

    @Autowired
    private EwalletLinkedDao elDao;

    @GetMapping("")
    public ResponseEntity<?> getListUser() {
        List<Customer> result = customerDao.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Kiểm tra thông tin khách hàng muốn liên kết từ bên đối tác
     * @param session
     * @param phone số điện thoại của khách hàng
     * @param account tài khoản ngân hàng của khách hàng
     * @param idCard số cmmt hoặc cccd của khách hàng
     * @param fullName họ và tên khách hàng
     * @return
     */
    @PostMapping("/check")
    public ResponseEntity<?> checkCustomer(HttpSession session, @RequestParam("phone") String phone,
    @RequestParam("account") String account, @RequestParam("idCard") int idCard, @RequestParam("fullName") String fullName){
        //Tạo 1 đối tượng transaction dùng để lưu hành động được gọi tới server
        Transaction objTransaction = new Transaction();

        objTransaction.setTrans_date(date);
        objTransaction.setTrans_type("Kiểm tra thông tin khách hàng liên kết ví");

        // Lấy thông tin khách hàng trong hệ thống
        Customer objCustomer = customerDao.findByIdCar(idCard);
        if (objCustomer != null){
            // Nếu khách hàng tồn tại thì kiểm tra thông tin tài khoản
            Account objAccount = accountDao.findByNameAndCif(account, objCustomer.getCif());

            // Nếu khách hàng tồn tại và thông tin trùng khớp thì trả về success
            if (objAccount != null){
                if (objCustomer.getPhone().equals(phone) &&  objCustomer.getFull_name().equals(fullName)){
                    session.setAttribute("objCustomer", objCustomer);
                    session.setAttribute("objAccount", objAccount);

                    // Lưu hành đọng vào trong hệ thống
                    objTransaction.setStatus("00");
                    objTransaction.setDescription("Thành công");
                    transactionDao.add(objTransaction);
                    return new ResponseEntity<Transaction>(objTransaction,HttpStatus.OK);
                }else{
                    objTransaction.setStatus("04");
                    objTransaction.setDescription("Họ tên hoặc số điện thoại không chính xác");
                    transactionDao.add(objTransaction);
                    return new ResponseEntity<Transaction>(objTransaction,HttpStatus.EXPECTATION_FAILED);
                }
            }else{
                objTransaction.setStatus("05");
                objTransaction.setDescription("Số tài khoản ngân hàng không tồn tại");
                transactionDao.add(objTransaction);
                return new ResponseEntity<Transaction>(objTransaction,HttpStatus.EXPECTATION_FAILED);
            }
        }
        // Lưu hành động vào trong hệ thống
        objTransaction.setStatus("03");
        objTransaction.setDescription("Số chứng minh thư không tồn tại");
        transactionDao.add(objTransaction);
        return new ResponseEntity<Transaction>(objTransaction,HttpStatus.EXPECTATION_FAILED);
    }

    /**
     * Kiểm tra tài khoản đăng nhập IB của khách hàng
     * @param session
     * @param userName tài khoản đăng nhập IB của khách hàng
     * @param passWord mật khẩu đăng nhập IB
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpSession session, @RequestParam("userName") String userName,
         @RequestParam("passWord") String passWord){
        //Tạo 1 đối tượng transaction dùng để lưu hành động được gọi tới server
        Transaction objTransaction = new Transaction();

        objTransaction.setTrans_date(date);
        objTransaction.setTrans_type("Kiểm tra đăng nhập");

        //Kiểm tra tài khoản đăng nhập
        User objUser = userDao.check(userName);
        if (objUser!= null){
            if (objUser.getPass_word().equals(passWord)){
                // Lưu hành động vào trong hệ thống
                objTransaction.setStatus("00");
                objTransaction.setDescription("Thành công");
                transactionDao.add(objTransaction);
                return new ResponseEntity<Transaction>(objTransaction, HttpStatus.OK);
            }else{
                objTransaction.setStatus("07");
                objTransaction.setDescription("Mật khẩu không chính xác");
                transactionDao.add(objTransaction);
                return new ResponseEntity<Transaction>(objTransaction, HttpStatus.EXPECTATION_FAILED);
            }
        }
        // Lưu hành động vào trong hệ thống
        objTransaction.setStatus("06");
        objTransaction.setDescription("Tên đăng nhập không chính xác");
        transactionDao.add(objTransaction);
        return new ResponseEntity<Transaction>(objTransaction, HttpStatus.EXPECTATION_FAILED);
    }


    /**
     * Kiểm tra mã xác thực Otp
     * @param session
     * @param otp otp khách hàng nhập
     * @return
     */
    @PostMapping("/otp")
    public ResponseEntity<?> otp(HttpSession session, @RequestParam("otp") int otp){
        //Tạo 1 đối tượng transaction dùng để lưu hành động được gọi tới server
        Transaction objTransaction = new Transaction();
        objTransaction.setTrans_type("Kiểm tra mã OTP");
        objTransaction.setTrans_date(date);

        //Lấy tài khoản của khách hàng đang thực hiện giao dịch từ sesion
        Account objAccount = (Account) session.getAttribute("objAccount");

        //Lấy mã otp của khách hàng
        OTP objOtp = otpDao.getOtpByAcc(objAccount.getId());

        // Kiểm tra mã otp khách hàng đã nhập
        if (objOtp.getOtp() == otp){

            // Lưu hành động vào trong hệ thống
            objTransaction.setStatus("00");
            objTransaction.setDescription("Thành công");
            transactionDao.add(objTransaction);

            //Gọi hàm thêm dữ liệu liên kết vào db
            createEwalletLinked(session);

            return new ResponseEntity<Transaction>(objTransaction, HttpStatus.OK);
        }
        // Lưu hành động vào trong hệ thống
        objTransaction.setStatus("08");
        objTransaction.setDescription("Mã OTP không chính xác");
        transactionDao.add(objTransaction);
        return new ResponseEntity<Transaction>(objTransaction, HttpStatus.EXPECTATION_FAILED);
    }

    public void createEwalletLinked(HttpSession session){
        //Lấy chi thông tin khách hàng đang thực hiện giao dịch liên kết từ session
        Customer objCus =(Customer) session.getAttribute("objCustomer");
        Account objAcc =(Account) session.getAttribute("objAccount");
        //Tạo 1 đối tượng transaction dùng để lưu hành động được gọi tới server
        Transaction objTransaction = new Transaction();
        objTransaction.setTrans_type("Tạo liên kết ví");
        objTransaction.setDescription("Thành công");
        objTransaction.setTrans_date(date);
        objTransaction.setStatus("00");
        transactionDao.add(objTransaction);

        //Tạo Ewallet Linked
        EwalletLinked objEL = new EwalletLinked();
        objEL.setAccount_number(objAcc.getAccount_number());
        objEL.setEwall_id(objCus.getPhone());
        objEL.setTrans_id(objTransaction.getId());
        objEL.setLinked_date(date);
        objEL.setStatus("00");
        elDao.add(objEL);
        System.out.println("id la"+objEL.getId());
        System.out.println(objEL.getAccount_number());
        CallRestful.callAPI(objEL);

        //Tạo Transaction Wallet
        TransactionEwallet objTW = new TransactionEwallet();
        TransactionEwalletId twId = new TransactionEwalletId();
        objTW.setAccount_number(objAcc.getAccount_number());
        twId.setTrans_id(objTransaction.getId());
        twId.setEwall_id(objCus.getPhone());
        objTW.setId(twId);
        objTW.setFull_name(objCus.getFull_name());
        objTW.setId_card(objCus.getId_card());
        teDao.add(objTW);
    }
}
