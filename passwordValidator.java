import java.security.SecureRandom;
//import com.fasterxml.jackson.databind.ObjectMapper;

public class passwordValidator {
    public static void main(String[] args) {
        tbBatchItem[] items = new tbBatchItem[]{
            new tbBatchItem("Susan Smith", "ssmith@comany1.com", "susan12%#?", "SuperUser", "Because I am \"cool\", I can do whatever I want."),
            new tbBatchItem("Alex O'Connor", "alexoconnor@univ1.edu","itsuniv1", "ReadOnly", "I need to access report for budget < 1M $"),
            new tbBatchItem("John J. Peterson","john.p@comany2.com", "J.Pe1234!", "Auditor", "Access to 1) all reports; 2) server system logs for \"Audit\" and [app]_Access_Log"),
            new tbBatchItem("Chen, Mei \u9648\u6885", "chehmei12@123.com", "<:-)>{;=0}", "ReadOnly", "\u6211\u8D1F\u8D23\u4E2D\u56FD\u5206\u516C\u53F8\u8D22\u52A1")
        };

        for(tbBatchItem item : items){
            System.out.println("\n\nName: " + item.name + "\nEmail: " + item.email+ "\nPassword: " + item.password);
            boolean valid =  validatePassword(item.name, item.email, item.password);
            if(!valid) {
                System.out.println("Password is not valid. A new password is generated: "+ generatePassword(8));
            } else {
                System.out.println("Password is valid");
            }
        }
        // jackson libraries are required for object/json conversion: jackson-annotations, jackson-core, jackson-databind
        //convertToJson(items);
    }

    /**
     * Generates password that meets the minimum requirements
     * 
     * @param passLength is the length of the generated password
     * @return generated password
     */
    private static String generatePassword(int passLength){
        String[] chars = new String[]{ 
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",  // upper case
            "abcdefghijklmnopqrstuvwxyz",  // lower case
            "0123456789",                  // numbers
            "!'?\"-:,;()[]{}",             // punctuations
             "~@#$%^&*+=|<>/\\"};          // symbols
        
        SecureRandom randomIndex = new SecureRandom();
        StringBuilder sb = new StringBuilder();
                
        while(sb.length()<passLength){
            int charsIndex = randomIndex.nextInt(chars.length);    
            int k = randomIndex.nextInt(chars[charsIndex].length());    
            sb.append(chars[charsIndex].charAt(k));
        }
        return sb.toString();  
    }

    /**
     * Validates password complexity 
     * 
     * @param name is to used to ensure that it is not found in the password
     * @param email is to used to ensure that it is not found in the password
     * @param password user provided password
     * @return
     */
    private static boolean validatePassword(String name, String email, String password){
        if(password.length()<8){
            System.out.println("\u2715 must be at least 8 character long");
            return false;
        }
        int minReq = 0;
        if( password.matches("(?=.*[0-9]).*")) {
            System.out.println("\u2714 contains numbers");
            minReq++;
        }
        if (password.matches("(?=.*[a-z]).*")){
            System.out.println("\u2714 contains lower case alphabet");
            minReq++;
        }
        if (password.matches("(?=.*[A-Z]).*")){
            System.out.println("\u2714 contains upper case alphabet");
            minReq++;
        }
        if (password.matches("(?=.*[!'?\"-:,;()\\[\\]{}]).*")){
            System.out.println("\u2714 contains punctuation characters");
            minReq++;
        }
        if (password.matches("(?=.*[~@#$%^&*+=|<>/\\\\]).*")){
            System.out.println("\u2714 contains symbols");
            minReq++;
        }    
        
        if(minReq<3){
            System.out.println("\u2715 doesn't contain characters from 3 of the 5 sets");
            return false;
        }

        // Check name components
        String passwordInLowCase = password.toLowerCase();
        String[] nameTokens = name.split("[,.\\-_#\\s]+");
        for(int i=0; i<nameTokens.length; i++) {
            String token = nameTokens[i];
            
            if(token.length() >= 3) {
                if(passwordInLowCase.contains(token.toLowerCase())){
                    System.out.println("\u2715 contains name");
                    return false;
                }
            }
        }


        // Check email components
        email = email.substring(0, email.indexOf('.'));
        String[] emailTokens = email.split("@");
        for (int i=0; i<emailTokens.length; i++) {
            String token = emailTokens[i];
            if(passwordInLowCase.contains(token.toLowerCase())){
                System.out.println("\u2715 contains email");
                return false;
            }
        }

        
        return true;
    }

    // jackson libraries are required for object/json conversion: jackson-annotations, jackson-core, jackson-databind
    /* 
    private static void convertToJson(tbBatchItem[] items){
        tbBatch tbRow = new tbBatch();
        tbRow.setTbBatchItems(items);
        ObjectMapper mapper = new ObjectMapper();
        try{
            String json = mapper.writeValueAsString(tbRow);
            System.out.println("\nJSON Formatted:\n" + json);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    */
}

class tbBatch {
    tbBatchItem[] tbBatchItems;
    public void setTbBatchItems(tbBatchItem[] items) {
        this.tbBatchItems = items;
    }
    public tbBatchItem[] getTbBatchItems() {
        return this.tbBatchItems;
    }
    
}

class tbBatchItem {
    String name;
    String email;
    String password;
    String role;
    String reasonForAccess;
    tbBatchItem (String inputName, String inputEmail, String inputPassword, String inputRole, String inputRfa){
        name = inputName;
        email = inputEmail;
        password = inputPassword;
        role = inputRole;
        reasonForAccess = inputRfa;

    }
    public void setName (String name){
        this.name = name;
    }
    public String getName (){
        return this.name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return this.role;
    }
    public void setReasonForAccess(String rfa) {
        this.reasonForAccess = rfa;
    }
    public String getReasonForAccess() {
        return this.reasonForAccess;
    }

}