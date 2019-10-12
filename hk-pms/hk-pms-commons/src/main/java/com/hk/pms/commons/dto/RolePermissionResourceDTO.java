package com.hk.pms.commons.dto;

import java.io.Serializable;
import java.util.Collection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@SuppressWarnings("serial")
@NoArgsConstructor
@Deprecated
public class RolePermissionResourceDTO implements Serializable {

	/**
	 * 角色名
	 */
	private String roleName;

	/**
	 * 权限
	 */
	private Collection<PermissionResourceDTO> permissionResources;

	@Data
	@NoArgsConstructor
	public static class PermissionResourceDTO implements Serializable {

		/**
		 * 权限
		 */
		private String permissionName;

		/**
		 * 菜单
		 */
		private Collection<ResourceDTO> resources;

		/**
		 * 
		 * @description 资源菜单
		 * @author huangkai
		 * @date 2019年1月28日 下午12:47:11
		 */
		@Data
		@NoArgsConstructor
		public static class ResourceDTO implements Serializable {

			/**
			 * 菜单Id
			 */
			private String id;

			/**
			 * 菜单名称
			 */
			private String resourceName;

			/**
			 * 菜单url
			 */
			private String resourceUrl;

			/**
			 * 菜单icon
			 */
			private String icon;

			/**
			 * <p>
			 * 菜单 target
			 * </p>
			 * 
			 * <pre>
			* _self
			* _blank
			* _top
			* _parent
			 * </pre>
			 */
			private String target;

			/**
			 * 子级菜单
			 */
			private Collection<ResourceDTO> childs;
		}
	}
}
