package com.hr.hotel.security;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.hr.hotel.repository.RolePermissionRepository;

public class CustomInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	// private RequestMatcher urlMatcher = new
	// private RequestMatcher matcher = null;

	// private static List<UrlResource> resource = null;

	// private final PermissionRepository permissionRepository;
	//private static Map<String, String[]> permissionRoles = null;

	private final RolePermissionRepository rolePermissionRepository;

	public CustomInvocationSecurityMetadataSource(
			RolePermissionRepository rolePermissionRepository) {
		// this.permissionRepository = permissionRepository;
		this.rolePermissionRepository = rolePermissionRepository;
		// loadResourceDefine();
	}

	// private void loadResourceDefine() {
	// List<Permission> objPermissions = null;
	// List<String> objPermissionRoles = null;
	// Collection<ConfigAttribute> objConfigAttributes = null;
	// ConfigAttribute objConfigAttribute = null;
	// UrlResource objUrlResource = null;
	// try {
	// resource = new ArrayList<UrlResource>();
	// // get all URL list from database
	// objPermissions = this.permissionRepository.getAllPermission();
	// // check list not empty
	// if (objPermissions != null && objPermissions.size() > 0) {
	// // loop through list
	// for (Permission permission : objPermissions) {
	// // get all role for given URL
	// objPermissionRoles = this.rolePermissionRepository
	// .getPermissionRoleName(permission.getId());
	// // check list not empty
	// if (objPermissionRoles != null
	// && objPermissionRoles.size() > 0) {
	// objConfigAttributes = new ArrayList<ConfigAttribute>();
	// // get role one by one
	// for (String role : objPermissionRoles) {
	// // add role to list
	// objConfigAttribute = new SecurityConfig(role);
	// objConfigAttributes.add(objConfigAttribute);
	// }
	// // add all param to list
	// objUrlResource = new UrlResource();
	// objUrlResource.setConfigAttributes(objConfigAttributes);
	// objUrlResource.setMethod(permission.getMethod());
	// objUrlResource.setUrl(permission.getUrlPattern());
	// resource.add(objUrlResource);
	// }
	// }
	// }
	// } finally {
	//
	// }
	// }

	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		FilterInvocation fi = (FilterInvocation) object;

		String url = fi.getRequestUrl();

		HttpServletRequest request = fi.getHttpRequest();

		// if (permissionRoles == null)
		// permissionRoles = new HashMap<String, String[]>();
		//
		// if (!permissionRoles.isEmpty()) {
		// Set<String> urls = permissionRoles.keySet();
		// for (String fUrl : urls) {
		// if (fUrl.equals(url)) {
		// return SecurityConfig.createList(permissionRoles.get(fUrl));
		// }
		// }
		// }

		if (url.contains("/css") || url.contains("/_img")
				|| url.contains("/js") || url.contains("/images"))
			return null;

		// Do not forget to add caching of the lookup
		List<String> roles = this.rolePermissionRepository.getRoles(url,
				request.getMethod());
		if (roles != null && !roles.isEmpty()) {
			String[] role = roles.toArray(new String[roles.size()]);
			//permissionRoles.put(url, role);
			return SecurityConfig.createList(role);
		}
		return null;
		// return SecurityConfig.createList(roles);

		// // guess object is a URL.
		// HttpServletRequest httpRequest = ((FilterInvocation) object)
		// .getHttpRequest();
		// // get all url from list and check against request
		// for (UrlResource urlResource : resource) {
		// // get url pattern
		// String resURL = urlResource.getUrl();
		// // get url method
		// String resMethod = urlResource.getMethod();
		// // create object of matcher to match request
		// this.matcher = new AntPathRequestMatcher(resURL, resMethod);
		// if (this.matcher.matches(httpRequest)) {
		// return urlResource.getConfigAttributes();
		// }
		// }
		// return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
