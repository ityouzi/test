package org.example;

/**
 * @author lizhen created on 2021-06-15 17:43
 */
public class xx {

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
//            // 1.创建家长信息
//
//            // 1.1查询本地是否存在该家长
//
//            // 1.2创建家长信息
//
//            // 1.3更新学生和家长关系
//            List<StudentParentRelation> relations = new ArrayList<>();
//            List<StudentParentRelation> relationList = studentParentRelationService.queryRelationByParentUserId(parent.getParent_userid());
//            relationList.forEach(relation->{
//                relation.setIsSubscribe(relation.getIsSubscribe());
//                relation.setParentId(家长ID);
//
//                relations.add(relation);
//
//            });
//
//            // 2.批量更新学生和家长关系
//            studentParentRelationService.batchRelation(relations);
//
//
//            log.info("该家长已订阅！parentUserId={}",parent.getParent_userid());
//            return;
//        }
    }







/**
 * 第一种情况（新家长）：扫码添加学生，此时触发【创建学生事件】，执行添加学生方法。而此时依据学生查询的结果，家长parents[]是空的，直接return.
 * 然后会触发【创建家长事件】，此时是依据家长ID查询的，家长的订阅状态是0。然后会触发【家长订阅事件】，此时是依据家长ID查询的，家长的
 * 订阅状态是1。
 *
 * 1.未订阅不处理
 *
 * 2.已订阅处理
 *  2.1 【创建学生事件】创建学生
 *  2.2 【创建家长事件】 创建学生和家长为完善的关系数据
 *  2.3 【家长订阅事件】 此时的订阅状态为 1 ，可以获取到家长外联系人详情，创建家长信息，批量更新学生和家长信息
 *
 *
 * 家长已存在（该家长名下已有其他学生入班）触发事件【创建学生事件】【更新家长事件】 此时的家长订阅状态是 1，
 * 1. 未订阅不处理
 *
 * 2.已订阅处理
 *  2.1.【创建学生事件】创建学生。判断学生关系是否存在，如果存在更新，不存在新增
 *  2.2 【更新家长事件】 更新学生和家长关系（具体更新称谓）
 *
 *
 *
 *
 *
 *
 *
 *
 * 因为企业微信会依据家长手机号判断
 * 第二中情况（手动加入：家长已存在）触发【更新家长事件】【创建学生事件】因为家长存在，家长的订阅状态是1
 * 1.未订阅不处理
 *
 * 2.已订阅处理
 *  2.1 查询学生和家长关系是否存在
 *  2.2 新增学生信息
 *  2.3 新增完整学生和家长关系
 *
 *
 *
 *
 *
 * 新的家长手机号
 * 第三种情况（手动加入：家长不存在）此时教师手动加入学生后，此时触发了【创建学生事件】【创建家长事件】。
 * 教师通过给家长发送入班二维码，家长使用微信扫码，触发了【家长订阅事件】
 * 代码步骤：首先判断该家长是否订阅
 * 1.未订阅不处理
 *
 * 2.已订阅处理
 *  2.1 查询本地是否存在该家长
 *  2.2 创建家长信息
 *  2.3 依据家长ID查询学生和家长关系，构建完整关系
 *  2.4 批量更新学生和家长关系
 *
 *
 * 教师手动添加学生,家长已存在13627271232  企业微信提示 [未加入]  ,本地需要加一条未完善关系?
 *
 *
 *
 */



/**
 * 企业微信【编辑学生家长】
 *
 *  [业微信推送删除家长事件] [删除学生事件]  异常
 *
 *
 *
 *
 *
 * 手动加入,
 *
 *
 *
 *
 *
 *
 *
 */




/**
 * 1.家长端进入后,点击订阅按钮,后端需要更新该家长信息,订阅字段.,然后依据该家长是否订阅,推送[作业,公告]
 * 给家长微信.
 *
 * 2.教师发布作业和公告成功后,返回ID
 *
 * 3.修改公告
 *
 * 1.新增更新家长表的家长关注状态（订阅，取消订阅）
 * 2.
 *
 *
 *
 *
 *
 * 2021-08-10
 * 同一个企业下，同一个学生，存在多个班级中
 * 企微学生ID一样，企业ID一样
 *
 *
 * 第一种情况：学生不存在，学生可能有多个班级关系，最少一个。依据企微学生ID和企业ID查询学生详情，循环班级ID
 * （新增学生事件）
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */



/**
 * 企微【创建学生事件】
 *
 * @author lizhen
 * @date 2021/8/10-14:19
 *
 * 1.依据回调事件的企微学生ID和企业ID查询企微学生对象信息
 * 2.获取学生对象集合中的部门ID，依据部门ID和企业ID查询本地班级信息（如果不存在，从新初始化一条班级数据）
 * 3.依据企微学生对象，构建本地学生数据
 * 4.构建学生和班级关联数据
 * 5.判断企微学生对象中家长集合是否有数据
 *
 *  a:有数据时，循环家长集合，同时判断该家长是否订阅过
 *  未订阅：依据本地学生ID和企微家长的userid查询本地是否存在学生和家长关系。不存在则新增一条“未完善”学生和家长关系
 *
 *  已订阅：依据企微家长userid获取企微“外部联系人详情”，然后依据企微“unionid”查询本地家长表，判断家长是否存在，不存在则新增家长数据。
 *         然后查询学生和家长关系，判断是否存在，不存在则新增一条“完善”的学生和家长关系，存在则更新该条数据为“完善”关系。
 *  b:没数据时，不处理return
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/**
 * 企微【删除学生家长】
 *
 * @author lizhen
 * @date 2021/8/10-15:08
 *
 * 调用事件【更新家长：此时家长已经不存在】【删除家长】【删除学生】
 *
 *
 *
 *
 *
 *
 *
 *
 */










