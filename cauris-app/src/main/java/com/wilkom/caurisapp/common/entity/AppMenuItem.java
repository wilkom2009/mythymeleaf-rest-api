package com.wilkom.caurisapp.common.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "app_menu_items")
public class AppMenuItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8432414340180447723L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@Column(name = "menu_label")
	private String menuLabel;
	
	@Column(name = "menu_permission_label")
	private String menuPermissionLabel;
	
	@Column
	private String outcome;

	@ManyToOne
	private AppMenuItem topMenu;// null if menu, not null if menuItem ==> topMenu is the menu for this menuItem
	
	@OneToMany
	private List<AppMenuItem> menuItems = new LinkedList<>();

	
	public AppMenuItem(String menuLabel, String menuPermissionLabel, String outcome, AppMenuItem topMenu) {
		super();
		this.menuLabel = menuLabel;
		this.menuPermissionLabel = menuPermissionLabel;
		this.outcome = outcome;
		this.topMenu = topMenu;
	}

	public AppMenuItem() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuLabel() {
		return menuLabel;
	}

	public void setMenuLabel(String menuLabel) {
		this.menuLabel = menuLabel;
	}

	public AppMenuItem getTopMenu() {
		return topMenu;
	}

	public void setTopMenu(AppMenuItem topMenu) {
		this.topMenu = topMenu;
	}

	public String getMenuPermissionLabel() {
		return menuPermissionLabel;
	}

	public void setMenuPermissionLabel(String menuPermissionLabel) {
		this.menuPermissionLabel = menuPermissionLabel;
	}

	public List<AppMenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<AppMenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((menuLabel == null) ? 0 : menuLabel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppMenuItem other = (AppMenuItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (menuLabel == null) {
			if (other.menuLabel != null)
				return false;
		} else if (!menuLabel.equals(other.menuLabel))
			return false;
		return true;
	}

	/**
	 * 
	 * @return the wildcare permission name for this shiro
	 */
	public String getAppMenuItemPermissionName() {
		return topMenu == null ? menuPermissionLabel : topMenu.getAppMenuItemPermissionName() + ":" + menuPermissionLabel;
	}

	@Override
	public String toString() {
		return menuLabel;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	
}
