package org.example;

/**
 * @author lizhen created on 2021-06-15 17:43
 */
public class ParentInfoManageImpl {
//    @Autowired
//    private ParentInfoService parentInfoService;
//    @Autowired
//    private QyWechatInfo qyWechatInfo;
//    @Autowired
//    private QyWechatManager qyWechatManager;
//    @Autowired
//    private StudentParentRelationService studentParentRelationService;
//    @Autowired
//    private StudentService studentService;
//
//
//    /**
//     * 删除家长
//     *
//     * @param id 家长id
//     * @param corpId 企业ID
//     * @return
//     * @author: lizhen
//     * @date: 2021/5/25-14:35
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void deleteParentInfoByUserId(String id, String corpId) {
//        JsonRootBeanParent parentInfoAndChilded = this.buildJsonRootBeanParent(id, corpId);
//        String externalUserId = parentInfoAndChilded.getParent().getExternal_userid();
//        ParentInfo parentInfo = parentInfoService.seletByExterUserId(externalUserId);
//        if (ObjectUtil.isNull(parentInfo)){
//            log.info("本地没有查询到家长信息！externalUserId={}", externalUserId);
//            return;
//        }
//        // 删除学生和家长关系
//        if (CollectionUtils.isEmpty(parentInfoAndChilded.getParent().getChildren())){
//            // 删除家长信息
//            int row = parentInfoService.deleteByPrimaryKey(parentInfo.getId());
//            Assert.isTrue(row > 0, "删除家长信息成功！ id="+parentInfo.getId());
//            return;
//        }
//        List<Children> childrenList = parentInfoAndChilded.getParent().getChildren();
//        childrenList.forEach(children -> {
//            WechatStudent wechatStudent = studentService.queryStudentByUserId(children.getStudent_userid());
//            if (ObjectUtil.isNull(wechatStudent)){
//                log.info("学生信息不存在！studentUserId={}",children.getStudent_userid());
//                throw new BusinessException("学生信息不存在");
//            }
//            studentParentRelationService.deleteRelationByStudentIdAndParentId(wechatStudent.getId(),parentInfo.getId());
//        });
//    }
//
//    private JsonRootBeanParent buildJsonRootBeanParent(String id, String corpId){
//        return qyWechatManager.getParentInfoAndChilder(id, corpId);
//    }
//
//    /**
//     * 创建家长
//     *
//     * @param content
//     * @return
//     * @author: lizhen
//     * @date: 2021/5/25-19:16
//     */
//    @Override
//    public void createParent(External_contact content) {
//        ParentInfo parentInfo = this.buildParentInfo(content);
//        int row = parentInfoService.insert(parentInfo);
//        Assert.isTrue(row > 0,"新增家长失败！parentId="+content.getUnionid());
//    }
//
//    /**
//     * 更新家长信息
//     *
//     * @param content
//     * @param corpId
//     * @return
//     * @author: lizhen
//     * @date: 2021/5/26-18:13
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void queryParentInfoByUnionId(JsonRootBeanParent content, String corpId) {
//        Parent contentParent = content.getParent();
//        log.info("家长UserId={}",contentParent.getExternal_userid());
//        JsonRootBeanExternal externalUserId = qyWechatManager.getExternalUserId(contentParent.getExternal_userid(), corpId);
//        log.info("家长UnionID={}",externalUserId.getExternal_contact().getUnionid());
//        ParentInfo parentInfo = parentInfoService.seletByExterUserId(externalUserId.getExternal_contact().getUnionid());
//        if (ObjectUtil.isNull(parentInfo)){
//            int row = parentInfoService.insert(this.buildParentInfo(externalUserId.getExternal_contact()));
//            Assert.isTrue(row > 0,"添加家长信息失败！");
//        }
//        // 更新学生家长称谓
//        List<Children> childrenList = contentParent.getChildren();
//        childrenList.forEach(children -> {
//            WechatStudent wechatStudent = studentService.queryStudentByUserId(children.getStudent_userid());
//            if (ObjectUtil.isNull(wechatStudent)){
//                log.info("没有查询到学生信息！studentUserId={}",children.getStudent_userid());
//                throw new BusinessException("没有查询到学生信息!");
//            }
//            StudentParentRelation relation = studentParentRelationService.queryRelationByStudentIdAndParentId(wechatStudent.getId(),parentInfo.getId());
//            if (ObjectUtil.isEmpty(relation)){
//                log.info("没有查询到学生和家长关系！studentId={}",wechatStudent.getId());
//                throw new BusinessException("没有查询到学生和家长关系");
//            }
//            relation.setRelation(children.getRelation());
//            studentParentRelationService.updateRelation(relation);
//        });
//    }
//
//    /**
//     * 查询家长信息
//     *
//     * @param unionId
//     * @return
//     * @author: lizhen
//     * @date: 2021/6/1-14:20
//     */
//    @Override
//    public ParentInfo queryParentByUnionId(String unionId) {
//        return parentInfoService.selectByUnionId(unionId);
//    }
//
//    /**
//     * @param rootBeanParent
//     * @param corpId
//     * @return
//     * @author: lizhen
//     * @date: 2021/6/15-16:54
//     */
//    @Override
//    public void createParentInfo(JsonRootBeanParent rootBeanParent, String corpId) {
//        Parent parent = rootBeanParent.getParent();
//        List<Children> children = parent.getChildren();
//        // 未订阅
//        if (ParentIsSubscribeEnum.NOT_SBSCRIBE.getStatus().equals(parent.getIs_subscribe())){
//
////            children.forEach(student->{
////                // 1.查询学生
////                WechatStudent ws = studentService.queryStudentByUserId(student.getStudent_userid());
////                // 2.查询学生和家长关系
////                StudentParentRelation studentParentRelation = studentParentRelationService.queryRelationByStudentIdAndRelation(ws.getId(), parent.getParent_userid());
////                if (ObjectUtil.isEmpty(studentParentRelation)){
////                    studentParentRelationService.createStudentAndParentRelation(this.buildStudentAndParentRelation(ws.getId(),parent,student.getRelation(),null));
////                }
////            });
//            log.info("该家长未订阅！parentUserId={}",parent.getParent_userid());
//            return;
//        }
//
//
//        // 已订阅
//        else{
//            JsonRootBeanExternal externalUserInfo = qyWechatManager.getExternalUserInfo(parent.getExternal_userid(), corpId);
//            children.forEach(student->{
//                WechatStudent ws = studentService.queryStudentByUserId(student.getStudent_userid());
//                // 1.查询家长
//                ParentInfo pf = parentInfoService.selectByUnionId(externalUserInfo.getExternal_contact().getUnionid());
//                if (ObjectUtil.isEmpty(pf)){
//                    pf = this.buildParentInfo(externalUserInfo);
//                    parentInfoService.insert(pf);
//                }
//                // 2.查询学生和家长关系
//                StudentParentRelation studentParentRelation = studentParentRelationService.queryRelationByStudentIdAndRelation(ws.getId(), parent.getParent_userid());
//                if (ObjectUtil.isEmpty(studentParentRelation)){
//                    studentParentRelationService.createStudentAndParentRelation(this.buildStudentAndParentRelation(ws.getId(),parent,student.getRelation(),pf.getId()));
//                }
//            });
//            log.info("该家长已订阅！parentUserId={}",parent.getParent_userid());
//            return;
//        }
//    }
//
//    /**
//     * 家长订阅事件
//     *
//     * @param parentUserId
//     * @param corpId
//     * @return
//     * @author: lizhen
//     * @date: 2021/6/15-17:31
//     */
//    @Override
//    public void subscribe(String parentUserId, String corpId) {
//        JsonRootBeanParent rootBeanParent = qyWechatManager.queryParentUserId(parentUserId, corpId);
//        Parent parent = rootBeanParent.getParent();
//        log.info("家长订阅状态！is_subscribe={}", parent.getIs_subscribe());
//
//        // 未订阅
//        if (ParentIsSubscribeEnum.NOT_SBSCRIBE.getStatus().equals(parent.getIs_subscribe())){
//            log.info("该家长未订阅！parentUserId={}",parent.getParent_userid());
//            return;
//        }
//
//        // 已订阅
//        else{
//            List<StudentParentRelation> relationList = studentParentRelationService.queryRelationByParentUserId(parent.getParent_userid());
//            relationList.forEach(relation->{
//                relation.setIsSubscribe(relation.getIsSubscribe());
//
//            });
//            log.info("该家长已订阅！parentUserId={}",parent.getParent_userid());
//            return;
//        }
//    }
//
//
//    /**
//     * 构架家长对象
//     *
//     * @author: lizhen
//     * @date: 2021/5/26-19:35
//     * @return
//     */
//    private ParentInfo buildParentInfo(External_contact content){
//        Date nowDate = DateUtils.getNowDate();
//        ParentInfo parentInfo = new ParentInfo();
//        parentInfo.setNickName(content.getName());
//        parentInfo.setUnionid(content.getUnionid());
//        parentInfo.setGender(content.getGender());
//        parentInfo.setCreateTime(nowDate);
//        parentInfo.setUpdateTime(nowDate);
//        return parentInfo;
//    }
//
//    /**
//     * 新增一条为完善关系信息
//     *
//     * @author: lizhen
//     * @date: 2021/6/9-17:08
//     * @return
//     */
//    private StudentParentRelation buildStudentAndParentRelation(Integer studentId, Parent parent,String re,Integer parentId){
//        Date nowDate = DateUtils.getNowDate();
//        StudentParentRelation relation = StudentParentRelation.builder()
//                .studentId(studentId)
//                .parentId(parentId)
//                .isSubscribe(parent.getIs_subscribe())
//                .relation(re)
//                .externalUserid(parent.getExternal_userid())
//                .parentUserId(parent.getParent_userid())
//                .createTime(nowDate)
//                .build();
//        return relation;
//    }
//
//    /**
//     *
//     *
//     * @author: lizhen
//     * @date: 2021/6/15-17:17
//     * @return
//     */
//    private ParentInfo buildParentInfo( JsonRootBeanExternal externalUserInfo){
//        External_contact contact = externalUserInfo.getExternal_contact();
//        Date nowDate = DateUtils.getNowDate();
//        ParentInfo pf = ParentInfo.builder()
//                .nickName(contact.getName())
//                .gender(contact.getGender())
//                .unionid(contact.getUnionid())
//                .extUserid(contact.getExternal_userid())
//                .createTime(nowDate)
//                .updateTime(nowDate)
//                .build();
//        return pf;
//    }

}

