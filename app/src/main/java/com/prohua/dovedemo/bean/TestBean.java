package com.prohua.dovedemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * json类
 * Created by Deep on 2018/1/16 0016.
 */

public class TestBean implements Serializable {

    private List<TeacherListBean> teacher_list;
    private List<GoodsListBean> goods_list;
    private List<QueryListBean> query_list;
    private List<MonthresultBean> monthresult;
    private List<?> advert_list;

    public List<TeacherListBean> getTeacher_list() {
        return teacher_list;
    }

    public void setTeacher_list(List<TeacherListBean> teacher_list) {
        this.teacher_list = teacher_list;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public List<QueryListBean> getQuery_list() {
        return query_list;
    }

    public void setQuery_list(List<QueryListBean> query_list) {
        this.query_list = query_list;
    }

    public List<MonthresultBean> getMonthresult() {
        return monthresult;
    }

    public void setMonthresult(List<MonthresultBean> monthresult) {
        this.monthresult = monthresult;
    }

    public List<?> getAdvert_list() {
        return advert_list;
    }

    public void setAdvert_list(List<?> advert_list) {
        this.advert_list = advert_list;
    }

    public static class TeacherListBean {
        /**
         * zip :
         * profession :
         * evaluate_score : 42
         * teacher_name : 杨昆霞
         * id_number : 420102195410202465
         * address : 广东省江门市蓬江区建设路9号之三505
         * education :
         * teacher_id : d6771df63f8c40d092cfb2fe2bf0ddbb
         * telephone :
         * source :
         * school_max :
         * img_name : af3cf6ef37f244f4b0233f8930639e37.jpg
         * school :
         * phone :
         * img_path : uploadFiles/uploadImgs/d6771df63f8c40d092cfb2fe2bf0ddbb/
         * user_id : d6771df63f8c40d092cfb2fe2bf0ddbb
         * bz :
         * id_type : 70dbd05695ae4927ba8eb9abda0ea08d
         * position :
         * state : 0
         * add_time : 1522207773000
         * user : {"img_name":"af3cf6ef37f244f4b0233f8930639e37.jpg","user_id":"d6771df63f8c40d092cfb2fe2bf0ddbb","img_path":"uploadFiles/uploadImgs/d6771df63f8c40d092cfb2fe2bf0ddbb/","nickName":"杨昆霞","userName":"杨昆霞"}
         * info : <p><strong>杨昆霞老师</strong></p><p><strong><br/></strong></p><p>具有教师资格证书及相关的专业技能证书，</p><p><br/></p><p>长期从事安全培训类工作，积累相当丰富的经验。一直以来在课堂上讲课，深受学员的欢迎和爱戴。讲课内容生动，并切合实际考试内容，是协会和学员推荐的好老师。</p>
         */

        private String zip;
        private String profession;
        private int evaluate_score;
        private String teacher_name;
        private String id_number;
        private String address;
        private String education;
        private String teacher_id;
        private String telephone;
        private String source;
        private String school_max;
        private String img_name;
        private String school;
        private String phone;
        private String img_path;
        private String user_id;
        private String bz;
        private String id_type;
        private String position;
        private String state;
        private long add_time;
        private UserBean user;
        private String info;

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public int getEvaluate_score() {
            return evaluate_score;
        }

        public void setEvaluate_score(int evaluate_score) {
            this.evaluate_score = evaluate_score;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getTeacher_id() {
            return teacher_id;
        }

        public void setTeacher_id(String teacher_id) {
            this.teacher_id = teacher_id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSchool_max() {
            return school_max;
        }

        public void setSchool_max(String school_max) {
            this.school_max = school_max;
        }

        public String getImg_name() {
            return img_name;
        }

        public void setImg_name(String img_name) {
            this.img_name = img_name;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        public String getId_type() {
            return id_type;
        }

        public void setId_type(String id_type) {
            this.id_type = id_type;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public static class UserBean {
            /**
             * img_name : af3cf6ef37f244f4b0233f8930639e37.jpg
             * user_id : d6771df63f8c40d092cfb2fe2bf0ddbb
             * img_path : uploadFiles/uploadImgs/d6771df63f8c40d092cfb2fe2bf0ddbb/
             * nickName : 杨昆霞
             * userName : 杨昆霞
             */

            private String img_name;
            private String user_id;
            private String img_path;
            private String nickName;
            private String userName;

            public String getImg_name() {
                return img_name;
            }

            public void setImg_name(String img_name) {
                this.img_name = img_name;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getImg_path() {
                return img_path;
            }

            public void setImg_path(String img_path) {
                this.img_path = img_path;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }

    public static class GoodsListBean {
        /**
         * goods_name : 免费体验课程
         * level : 初级
         * goods_price : 0.0
         * evaluate_num : 0
         * goods_id : f45c579ce60a40c3956d866a2850e2c5
         * study_num : 3
         * vip_price : 0.0
         * goods_salenum : 3
         * img_name : 30e718fcd10441ad8f3e2f4eccbcdb35.jpeg
         * img_path : uploadFiles/uploadImgs/20180516/
         */

        private String goods_name;
        private String level;
        private double goods_price;
        private int evaluate_num;
        private String goods_id;
        private int study_num;
        private double vip_price;
        private int goods_salenum;
        private String img_name;
        private String img_path;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public int getEvaluate_num() {
            return evaluate_num;
        }

        public void setEvaluate_num(int evaluate_num) {
            this.evaluate_num = evaluate_num;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public int getStudy_num() {
            return study_num;
        }

        public void setStudy_num(int study_num) {
            this.study_num = study_num;
        }

        public double getVip_price() {
            return vip_price;
        }

        public void setVip_price(double vip_price) {
            this.vip_price = vip_price;
        }

        public int getGoods_salenum() {
            return goods_salenum;
        }

        public void setGoods_salenum(int goods_salenum) {
            this.goods_salenum = goods_salenum;
        }

        public String getImg_name() {
            return img_name;
        }

        public void setImg_name(String img_name) {
            this.img_name = img_name;
        }

        public String getImg_path() {
            return img_path;
        }

        public void setImg_path(String img_path) {
            this.img_path = img_path;
        }
    }

    public static class QueryListBean {
        /**
         * goods_name : 生产经营单位安全生产管理人员（初训）
         * update_time : 1527730509000
         * query_id : 8d13144a85f14fbe9323e0cdf6f38352
         * user_id : 674ad95bbce141ad817c57407ee0635a
         * praise_num : 0
         * discuss_num : 2
         * goods_id : 858e2917021d41d6b14bfd256310e89d
         * add_time : 1527730481000
         * teacher_reply : 214d1d1f759049789abfedef365cd5f2
         * user : {"login_type":"WeChat","id_card":"440711198005113018","availableBalance":0,"front_acc_id":"3141190aec6d4edf83a5541ff8105acd","password":"ae04064356d5238e18d818bbde369b2b","learning_time":0,"img_name":"674ad95bbce141ad817c57407ee0635a.jpg","role_id":"3","integral":1,"bz":"swatchan133","back_acc_name":"uploadFiles/uploadImgs/674ad95bbce141ad817c57407ee0635aback_img/03a506be95204bff873a04c6f1151433.jpg","logo":"uploadFiles/uploadImgs/674ad95bbce141ad817c57407ee0635a/674ad95bbce141ad817c57407ee0635a.jpg","commission":0,"company":"共升教育","is_pass":0,"email":"swatchan@139.com","openid":"o_cxM5bdV2FnlknvWkHOKKr2JL2k","last_login":"2018-05-31 10:33:20","nickName":"共升教育安训通","sex":"0","userName":"13356596207","freezeBlance":0,"user_id":"674ad95bbce141ad817c57407ee0635a","phone":"13356596207","createTime":1526445101000,"img_path":"uploadFiles/uploadImgs/674ad95bbce141ad817c57407ee0635a/","front_acc_name":"uploadFiles/uploadImgs/674ad95bbce141ad817c57407ee0635afront_img/265478eb8f5749eb9124bec167047343.jpg","name":"共升教育安训通","back_acc_id":"ddadf05ea2a74adcb80a66ab3322ad5d","acc_id":"578064408dda46a8bcc9766e169376dd","status":"1"}
         * content : 老师，有什么新的政策啊？
         */

        private String goods_name;
        private long update_time;
        private String query_id;
        private String user_id;
        private int praise_num;
        private int discuss_num;
        private String goods_id;
        private long add_time;
        private String teacher_reply;
        private UserBeanX user;
        private String content;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }

        public String getQuery_id() {
            return query_id;
        }

        public void setQuery_id(String query_id) {
            this.query_id = query_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getPraise_num() {
            return praise_num;
        }

        public void setPraise_num(int praise_num) {
            this.praise_num = praise_num;
        }

        public int getDiscuss_num() {
            return discuss_num;
        }

        public void setDiscuss_num(int discuss_num) {
            this.discuss_num = discuss_num;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public String getTeacher_reply() {
            return teacher_reply;
        }

        public void setTeacher_reply(String teacher_reply) {
            this.teacher_reply = teacher_reply;
        }

        public UserBeanX getUser() {
            return user;
        }

        public void setUser(UserBeanX user) {
            this.user = user;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public static class UserBeanX {
            /**
             * login_type : WeChat
             * id_card : 440711198005113018
             * availableBalance : 0.0
             * front_acc_id : 3141190aec6d4edf83a5541ff8105acd
             * password : ae04064356d5238e18d818bbde369b2b
             * learning_time : 0
             * img_name : 674ad95bbce141ad817c57407ee0635a.jpg
             * role_id : 3
             * integral : 1
             * bz : swatchan133
             * back_acc_name : uploadFiles/uploadImgs/674ad95bbce141ad817c57407ee0635aback_img/03a506be95204bff873a04c6f1151433.jpg
             * logo : uploadFiles/uploadImgs/674ad95bbce141ad817c57407ee0635a/674ad95bbce141ad817c57407ee0635a.jpg
             * commission : 0.0
             * company : 共升教育
             * is_pass : 0
             * email : swatchan@139.com
             * openid : o_cxM5bdV2FnlknvWkHOKKr2JL2k
             * last_login : 2018-05-31 10:33:20
             * nickName : 共升教育安训通
             * sex : 0
             * userName : 13356596207
             * freezeBlance : 0.0
             * user_id : 674ad95bbce141ad817c57407ee0635a
             * phone : 13356596207
             * createTime : 1526445101000
             * img_path : uploadFiles/uploadImgs/674ad95bbce141ad817c57407ee0635a/
             * front_acc_name : uploadFiles/uploadImgs/674ad95bbce141ad817c57407ee0635afront_img/265478eb8f5749eb9124bec167047343.jpg
             * name : 共升教育安训通
             * back_acc_id : ddadf05ea2a74adcb80a66ab3322ad5d
             * acc_id : 578064408dda46a8bcc9766e169376dd
             * status : 1
             */

            private String login_type;
            private String id_card;
            private double availableBalance;
            private String front_acc_id;
            private String password;
            private int learning_time;
            private String img_name;
            private String role_id;
            private int integral;
            private String bz;
            private String back_acc_name;
            private String logo;
            private double commission;
            private String company;
            private int is_pass;
            private String email;
            private String openid;
            private String last_login;
            private String nickName;
            private String sex;
            private String userName;
            private double freezeBlance;
            private String user_id;
            private String phone;
            private long createTime;
            private String img_path;
            private String front_acc_name;
            private String name;
            private String back_acc_id;
            private String acc_id;
            private String status;

            public String getLogin_type() {
                return login_type;
            }

            public void setLogin_type(String login_type) {
                this.login_type = login_type;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public double getAvailableBalance() {
                return availableBalance;
            }

            public void setAvailableBalance(double availableBalance) {
                this.availableBalance = availableBalance;
            }

            public String getFront_acc_id() {
                return front_acc_id;
            }

            public void setFront_acc_id(String front_acc_id) {
                this.front_acc_id = front_acc_id;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public int getLearning_time() {
                return learning_time;
            }

            public void setLearning_time(int learning_time) {
                this.learning_time = learning_time;
            }

            public String getImg_name() {
                return img_name;
            }

            public void setImg_name(String img_name) {
                this.img_name = img_name;
            }

            public String getRole_id() {
                return role_id;
            }

            public void setRole_id(String role_id) {
                this.role_id = role_id;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public String getBz() {
                return bz;
            }

            public void setBz(String bz) {
                this.bz = bz;
            }

            public String getBack_acc_name() {
                return back_acc_name;
            }

            public void setBack_acc_name(String back_acc_name) {
                this.back_acc_name = back_acc_name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public double getCommission() {
                return commission;
            }

            public void setCommission(double commission) {
                this.commission = commission;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public int getIs_pass() {
                return is_pass;
            }

            public void setIs_pass(int is_pass) {
                this.is_pass = is_pass;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getLast_login() {
                return last_login;
            }

            public void setLast_login(String last_login) {
                this.last_login = last_login;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public double getFreezeBlance() {
                return freezeBlance;
            }

            public void setFreezeBlance(double freezeBlance) {
                this.freezeBlance = freezeBlance;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getImg_path() {
                return img_path;
            }

            public void setImg_path(String img_path) {
                this.img_path = img_path;
            }

            public String getFront_acc_name() {
                return front_acc_name;
            }

            public void setFront_acc_name(String front_acc_name) {
                this.front_acc_name = front_acc_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBack_acc_id() {
                return back_acc_id;
            }

            public void setBack_acc_id(String back_acc_id) {
                this.back_acc_id = back_acc_id;
            }

            public String getAcc_id() {
                return acc_id;
            }

            public void setAcc_id(String acc_id) {
                this.acc_id = acc_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }

    public static class MonthresultBean {
        /**
         * user_id : 36dc64c789c44925aaac6915c39ea28f
         * monthresult_id : 38fd1996b49f46e5a8c7eb31ff598bf1
         * title : 2018江门市安信职业安全培训有限公司安全生产管理人员危险化学品生产单位复训1期次全部考生成绩单
         * createdtime : 2018-05-08 17:24:47
         * content : [{"name":"陆自忠","card_num":"201800000984","bishi":"85.0","jishi":""},{"name":"伍成俊","card_num":"201800000971","bishi":"91.0","jishi":""},{"name":"种强","card_num":"201800000979","bishi":"91.0","jishi":""},{"name":"邓锡培","card_num":"201800000982","bishi":"98.0","jishi":""},{"name":"邓兆洪","card_num":"201800000981","bishi":"88.0","jishi":""},{"name":"侯东旭","card_num":"201800000972","bishi":"84.0","jishi":""},{"name":"林志豪","card_num":"201800000975","bishi":"83.0","jishi":""},{"name":"罗国恩","card_num":"201800000980","bishi":"82.0","jishi":""},{"name":"冯应文","card_num":"201800000973","bishi":"83.0","jishi":""},{"name":"金实建","card_num":"201800000983","bishi":"88.0","jishi":""},{"name":"梁炎华","card_num":"201800000976","bishi":"84.0","jishi":""},{"name":"黄凤华","card_num":"201800000985","bishi":"77.0","jishi":""},{"name":"周贵理","card_num":"201800000974","bishi":"77.0","jishi":""},{"name":"江文松","card_num":"201800000977","bishi":"0.0","jishi":""},{"name":"王未清","card_num":"201800000978","bishi":"71.0","jishi":""}]
         */

        private String user_id;
        private String monthresult_id;
        private String title;
        private String createdtime;
        private List<ContentBean> content;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getMonthresult_id() {
            return monthresult_id;
        }

        public void setMonthresult_id(String monthresult_id) {
            this.monthresult_id = monthresult_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreatedtime() {
            return createdtime;
        }

        public void setCreatedtime(String createdtime) {
            this.createdtime = createdtime;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * name : 陆自忠
             * card_num : 201800000984
             * bishi : 85.0
             * jishi :
             */

            private String name;
            private String card_num;
            private String bishi;
            private String jishi;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCard_num() {
                return card_num;
            }

            public void setCard_num(String card_num) {
                this.card_num = card_num;
            }

            public String getBishi() {
                return bishi;
            }

            public void setBishi(String bishi) {
                this.bishi = bishi;
            }

            public String getJishi() {
                return jishi;
            }

            public void setJishi(String jishi) {
                this.jishi = jishi;
            }
        }
    }
}
