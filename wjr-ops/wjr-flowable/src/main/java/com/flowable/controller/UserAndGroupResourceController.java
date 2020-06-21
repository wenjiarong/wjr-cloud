package com.flowable.controller;

import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.ManagementService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.api.User;
import org.flowable.ui.common.model.GroupRepresentation;
import org.flowable.ui.common.model.ResultListDataRepresentation;
import org.flowable.ui.common.model.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 重写获取用户，用户组接口
 */
@RestController
@RequestMapping("/app")
public class UserAndGroupResourceController {

    @Autowired
    protected ManagementService managementService;
    @Autowired
    protected IdmIdentityService idmIdentityService;

    @RequestMapping(value = "/rest/editor-groups", method = RequestMethod.GET)
    public ResultListDataRepresentation getGroups(@RequestParam(required = false, value =
            "filter") String filter) {
        if (StringUtils.isNotBlank(filter)) {
            filter = filter.trim();
            String sql = "select * from ACT_ID_GROUP where NAME_ like #{name}";
            filter = "%" + filter + "%";
            List<Group> groups =
                    idmIdentityService.createNativeGroupQuery().sql(sql).parameter("name", filter).listPage(0, 10);
            List<GroupRepresentation> result = new ArrayList<>();
            for (Group group : groups) {
                result.add(new GroupRepresentation(group));
            }
            return new ResultListDataRepresentation(result);
        }
        return null;
    }

    @RequestMapping(value = "/rest/editor-users", method = RequestMethod.GET)
    public ResultListDataRepresentation getUsers(@RequestParam(value = "filter", required = false)
                                                         String filter) {
        if (StringUtils.isNotBlank(filter)) {
            filter = filter.trim();
            String sql = "select * from ACT_ID_USER where ID_ like #{id} or LAST_ like #{name}";
            filter = "%" + filter + "%";
            List<User> matchingUsers =
                    idmIdentityService.createNativeUserQuery().sql(sql).
                            parameter("id", filter).parameter("name", filter).listPage(0, 10);
            List<UserRepresentation>
                    userRepresentations = new ArrayList<>(matchingUsers.size());
            for (User user : matchingUsers) {
                userRepresentations.add(new UserRepresentation(user));
            }
            return new ResultListDataRepresentation(userRepresentations);
        }
        return null;
    }
}