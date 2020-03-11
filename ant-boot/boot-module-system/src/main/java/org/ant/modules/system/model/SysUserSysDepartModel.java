package org.ant.modules.system.model;

import lombok.Data;
import org.ant.modules.system.entity.SysDepart;
import org.ant.modules.system.entity.SysUser;

/**
 * 包含 SysUser 和 SysDepart 的 Model
 *
 * @author sunjianlei
 */
@Data
public class SysUserSysDepartModel {

    private SysUser sysUser;
    private SysDepart sysDepart;

}
