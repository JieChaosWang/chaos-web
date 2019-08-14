package com.my.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.my.test.controller.BaseController;
import com.my.test.mapper.AuthorityMapper;
import com.my.test.mapper.RoleAuthorityMapper;
import com.my.test.mapper.RoleMapper;
import com.my.test.mapper.RoleMapper;
import com.my.test.pojo.Authority;
import com.my.test.pojo.Role;
import com.my.test.pojo.Role;
import com.my.test.pojo.RoleAuthority;
import com.my.test.service.RoleService;
import com.my.test.util.BeeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wang on 2018/11/27.
 */
@Service
public class RoleServiceImpl implements RoleService {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    /**
     * 权限角色
     * @param role 角色
     */
    @Transactional
    @Override
    public void updateRole(Role role) throws Exception {
        try{

            BeeUtils.isEmpty("角色主键", role.getRoleId());

            if (null != role.getAuthorityIds() && role.getAuthorityIds().size() > 0) {
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setRoles_id(role.getRoleId());

                roleAuthorityMapper.delRoleAuthority(roleAuthority);
            }

            List<Long> authorityIds = role.getAuthorityIds();

            for (Long authorityId : authorityIds) {

                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setRoles_id(role.getRoleId());
                roleAuthority.setAuthorities_id(authorityId);

                roleAuthorityMapper.addRoleAuthority(roleAuthority);

            }

            role.setModifyDate(new Date());
            roleMapper.updateRole(role);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Role queryRole(Role role) {

        role = roleMapper.queryRole(role);

        List<Authority> authorities = authorityMapper.queryAuthorityListByRoleId(role.getRoleId());

        role.setAuthorities(authorities);

        return role;
    }

    @Override
    public void addRole(Role role) throws Exception{

        try {

            roleMapper.addRole(role);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("新增统计记录失败", e);
        }

    }

    @Override
    public List<Role> queryRoleList(Role role){

        List<Role> list = list = roleMapper.queryRoleList(role);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw new Exception("新增统计记录失败", e);
//        }
        return list;
    }

    public static void main(String[] args) {

//        TestConverT<String, Integer> t = String::valueOf;
//        String i = t.convert(111);
//        System.out.println(i);

//        LocalDateTime dt = LocalDateTime.now();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
//
//        System.out.println(dtf.format(dt));


//		List<String> list = Arrays.asList("d","f","z","a");

//		list.forEach(s -> System.out.println(s));

//        list.forEach(s -> {
//            if ("a".equals(s)) {
//                System.out.println(s);
//            }
//        });

//        Map<String, String> map = new HashMap<>();
//        map.put("String1111111","1111111");
//        map.put("String2222222","2222222");
//        map.put("String3333333","3333333");
//        map.put("String4444444","4444444");
//        map.put("String5555555","5555555");
//        map.put("String6666666","6666666");
//        map.put("String7777777","7777777");
//        map.put("String8888888","8888888");
//
//
//        map.merge("String1111111","999999999", String::concat);
//
//        map.forEach((s, integer) -> {
//
//            System.out.println(s+"  :  "+integer);
//
//        });

        String str = "nnnnnabcmmmmmnnnnnabbcmmmmmnnnnnabbbcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabbcmmmmmnnnnnabbbcmmmmmnnnnnabcmmmmmnnnnnabbcmmmmmnnnnnabbbcmmmmmnnnnnabbcmmmmmnnnnnabcmmmmmnnnnnabbbcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabcmmmmmnnnnnabc";
        long l = System.currentTimeMillis();

        String[] split = str.split("ab{1,3}c");

        System.out.println(System.currentTimeMillis() - l);

        for (String thisS : split) {

            System.out.println(thisS);
        }




//		Collections.sort(list, (Comparator<? super String>) (String a, String b)->{
//					return a.compareTo(b);
//				}
//		);
//		for (String string : list) {
//			System.out.println(string);
//		}
//
//		Collections.sort(list,(o1, o2) -> o1.compareTo(o2));

//		int min = Math.min(150, 200);

//        String thisString = "a";
//        String anotherString = "b";
//
//        int i = compareTo(thisString, anotherString);


//        System.out.println(">>>>>>>>>>>" + min );

    }

    public static int compareTo(String thisString, String anotherString) {
        char[] chars = thisString.toCharArray();
        char[] anotherChars = anotherString.toCharArray();

        int len1 = chars.length;
        int len2 = anotherChars.length;
        int lim = Math.min(len1, len2);
        char v1[] = chars;
        char v2[] = anotherChars;

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

}
