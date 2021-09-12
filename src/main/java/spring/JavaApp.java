//package spring;
//
//import org.apache.commons.collections4.CollectionUtils;
//
///**
// * 启动
// *
// * @author lizhen created on 2021-08-06 15:44
// */
//public class JavaApp {
//    /**
//     * 授权数据同步
//     *
//     * @param corpId 企业ID
//     * @return
//     * @author: lizhen
//     * @date: 2021/6/4-9:35
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void dataSync(String corpId) {
//        log.info("【授权同步数据企业】ID={}", corpId);
//        if (StringUtils.isEmpty(corpId)){
//            throw new ParameterException("参数为空");
//        }
//        // 判断是否已经存在该企业
//        String accessToken = qyWechatManager.getAccessToken(corpId);
//        log.info("企业accessToken={}", accessToken);
//        JsonRootBeanDepartment rootBeanDepartment = qyWechatManager.queryDeptList(accessToken);
//        List<Departments> departmentsList = rootBeanDepartment.getDepartments();
//        if (CollectionUtils.isEmpty(departmentsList)){
//            log.info("获取企业全部部门列表失败！ 企业accessToken={}", accessToken);
//            return;
//        }
//        Departments dept = departmentsList.get(0);
//        // 根目录部门ID
//        Integer deptId = dept.getId();
//        String school = dept.getName();
//        log.info("学校名称={}", school);
//        // 不能做批量添加，会多次调用微信接口
//        departmentsList.forEach(departments -> {
//            // 班级，教师，关系
//            WechatClass wc = this.buildWechatClass(departments, corpId);
//            classService.callBackCreateDeptInfo(wc);
//            List<Department_admins> department_admins = departments.getDepartment_admins();
//            if (CollectionUtils.isEmpty(department_admins)){
//                log.info("该部门没有管理员！deptId={}, parentId={}", departments.getId(),departments.getParentid());
//                return;
//            }
//            department_admins.forEach(admin ->{
//                Teacher teacher = this.buildTeacher(admin, corpId,school);
//                String subject = null;
//                if (ObjectUtil.isNotNull(admin.getSubject())) {
//                    subject = admin.getSubject();
//                }
//                try {
//                    teacherService.callBackCreateTeacher(teacher);
//                }catch (Exception e) {
//                    log.error("教师已经存在 teacher = {}",teacher);
//                    teacher = teacherService.queryTeacherByUserIdId(teacher.getUserid(),teacher.getCorpid());
//                }
//                TeacherRelationClass teacherRelationClass = this.buildTeacherRelationClass(wc.getId(), teacher.getId(), admin.getType(), subject, corpId);
//                try {
//                    teacherRelationClassService.insertTeacherAndClassRelation(teacherRelationClass);
//                }catch (Exception e) {
//                    log.error("教师关联关系已经存在 teacherRelationClass = {}",teacherRelationClass);
//                }
//
//            });
//
//            // 学生，家长，关系
//            JsonRootBeanStudents rootBeanStudents = qyWechatManager.getDepartmentMemberInfo(corpId, departments.getId(), 0);
//            List<Students> students = rootBeanStudents.getStudents();
//            if (CollectionUtils.isEmpty(students)){
//                log.info("没有查询到该部门下的成员信息!", departments.getId());
//                return;
//            }
//
//            for (Students student : students){
//                if (StringUtils.isEmpty(student.getStudent_userid())){
//                    log.info("没有查询到部门学生和家长成员！");
//                    return;
//                }
//                WechatStudent wechatStudent = this.buildWechatStudent(student.getStudent_userid(), student.getName(), corpId, wc.getId());
//                studentService.callBackCreateStudentInfo(wechatStudent);
//                List<Parents> parents = student.getParents();
//                if (CollectionUtils.isEmpty(parents)){
//                    log.info("该学生下没有家长，信息异常！studentUserId={}", student.getStudent_userid());
//                    return;
//                }
//                parents.forEach(parent->{
//
//                    JsonRootBeanExternal externalDetails;
//                    if (ObjectUtil.isNull(parent.getExternal_userid())){
//                        Parent pt = this.queryParentUserId(parent.getParent_userid(), corpId);
//                        if (StringUtils.isEmpty(pt.getExternal_userid())){
//                            log.info("通过家长userId也没有查询到外部联系人id,家长userId={}",parent.getParent_userid());
//                            // 新增一条未完善关系
//                            StudentParentRelation studentParentRelation1 = studentParentRelationService.queryRelationByStudentIdAndRelation(wechatStudent.getId(), parent.getParent_userid());
//                            if (ObjectUtil.isNull(studentParentRelation1)){
//                                StudentParentRelation studentParentRelation = this.buildStudentAndParentRelation(wechatStudent.getId(), parent);
//                                studentParentRelationService.createStudentAndParentRelation(studentParentRelation);
//                                return;
//                            }
//                            return;
//                        }
//                        else {
//                            externalDetails = qyWechatManager.getExternalUserInfo(parent.getExternal_userid(), corpId);
//                        }
//                    }
//                    else {
//                        externalDetails = this.buildParentInfoUnionId(parent.getExternal_userid(), corpId);
//                    }
//
//                    // 查询家长表
//                    ParentInfo pf = parentInfoService.selectByUnionId(externalDetails.getExternal_contact().getUnionid());
//                    if (ObjectUtil.isNull(pf)){
//                        pf = this.buildParentInfo(externalDetails);
//                        parentInfoService.insert(pf);
//                        StudentParentRelation spr = studentParentRelationService.queryRelationByStudentIdAndRelation(wechatStudent.getId(), parent.getParent_userid());
//                        if (ObjectUtil.isNull(spr)){
//                            studentParentRelationService.createStudentAndParentRelation(this.buildStudentParentRelation(pf.getId(),wechatStudent.getId(),parent));
//                            return;
//                        }else {
//                            spr.setParentId(pf.getId());
//                            spr.setIsSubscribe(1);
//                            spr.setExternalUserid(pf.getExtUserid());
//                            studentParentRelationService.updateRelation(spr);
//                            return;
//                        }
//                    }
//                    else {
//                        StudentParentRelation spr = studentParentRelationService.queryRelationByStudentIdAndRelation(wechatStudent.getId(), parent.getParent_userid());
//                        if (ObjectUtil.isNull(spr)){
//                            studentParentRelationService.createStudentAndParentRelation(this.buildStudentParentRelation(pf.getId(),wechatStudent.getId(),parent));
//                            return;
//                        }
//                        else {
//                            spr.setParentId(pf.getId());
//                            spr.setIsSubscribe(1);
//                            spr.setExternalUserid(pf.getExtUserid());
//                            studentParentRelationService.updateRelation(spr);
//                            return;
//                        }
//                    }
//                });
//            }
//        });
//        log.info("授权同步企业微信班级数据成功！corpId={}", corpId);
//    }
//
//}
