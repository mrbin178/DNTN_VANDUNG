package ts24.com.vn.web.security;
//package ts24.com.vn.web.security;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import com.sevenj.model.UserRole;
//import com.sevenj.web.security.jass.UserPrincipal;
///**
// * This class is used to generate fake users
// * @author phuc.nguyen
// *
// */
//public class FakeUsers {
//
//	private Map<String,UserPrincipal> users;
//	public FakeUsers(){
//		Set<UserRole>userRole = new HashSet<UserRole>();
//		userRole.add(UserRole.ADMIN);
//		users = new HashMap<String, UserPrincipal>();
//		UserPrincipal user = new UserPrincipal("7jadmin",userRole);
//		user.setPassword("7jadmin");
//		user.setUserId(1);
//		users.put(user.getUserName(), user);
//		
//		userRole = new HashSet<UserRole>();
//		userRole.add(UserRole.BRS);
//		user = new UserPrincipal("7jbrs",userRole);
//		user.setPassword("7jbrs");
//		user.setUserId(2);
//		users.put(user.getUserName(), user);
//		
//		userRole = new HashSet<UserRole>();
//		userRole.add(UserRole.BBM);
//		user = new UserPrincipal("7jbbm",userRole);
//		user.setPassword("7jbbm");
//		user.setUserId(3);
//		users.put(user.getUserName(), user);
//		
//		userRole = new HashSet<UserRole>();
//		userRole.add(UserRole.EDIT);
//		user = new UserPrincipal("7jedit",userRole);
//		user.setPassword("7jedit");
//		user.setUserId(4);
//		users.put(user.getUserName(), user);
//		
//		userRole = new HashSet<UserRole>();
//		userRole.add(UserRole.VIEW_ONLY);
//		user = new UserPrincipal("7jview",userRole);
//		user.setPassword("7jview");
//		user.setUserId(5);
//		users.put(user.getUserName(), user);
//	}
//	public static UserPrincipal getUser(String userName){
//		FakeUsers fakeUsers = new FakeUsers();
//		return fakeUsers.users.get(userName);
//	}
//}
