package com.prohua.dovedemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * json类
 * Created by Deep on 2018/1/16 0016.
 */

public class TestBean implements Serializable {

    private List<GoodsListBean> goods_list;
    private List<QueryListBean> query_list;
    private List<AdvertListBean> advert_list;
    private List<MonthresultBean> monthresult;
    private List<TeacherListBean> teacher_list;

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

    public List<AdvertListBean> getAdvert_list() {
        return advert_list;
    }

    public void setAdvert_list(List<AdvertListBean> advert_list) {
        this.advert_list = advert_list;
    }

    public List<MonthresultBean> getMonthresult() {
        return monthresult;
    }

    public void setMonthresult(List<MonthresultBean> monthresult) {
        this.monthresult = monthresult;
    }

    public List<TeacherListBean> getTeacher_list() {
        return teacher_list;
    }

    public void setTeacher_list(List<TeacherListBean> teacher_list) {
        this.teacher_list = teacher_list;
    }

    public static class GoodsListBean implements Serializable {
        /**
         * goods_name : 韩国现代绞肉机家用电动不锈钢料理碎剁搅肉菜馅辣椒蒜泥器机小型
         * goods_price : 109
         * goods_id : 4c2351354f6c4f6ea276f24a59ea9e40
         * vip_price : 109
         * goods_salenum : 1
         * img_name : 4396b77fe07f4e6cbaa78d9b36bd30c9.jpg
         * img_path : uploadFiles/uploadImgs/20171201/
         */

        private String goods_name;
        private double goods_price;
        private String goods_id;
        private double vip_price;
        private int goods_salenum;
        private String img_name;
        private String img_path;

        private String level;
        private int study_num;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getStudy_num() {
            return study_num;
        }

        public void setStudy_num(int study_num) {
            this.study_num = study_num;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public double getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(double goods_price) {
            this.goods_price = goods_price;
        }

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
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

    public static class QueryListBean implements Serializable {
        /**
         * query_id : 1
         * user_id : 32666
         * praise_num : 0
         * discuss_num : 0
         * add_time : 1515999663000
         * user : {"img_name":"42fb9d5307504ec9bf1177dae0d542ed.png","user_id":"32666","img_path":"uploadFiles/uploadImgs/32666/","nickName":"Vincent","userName":"13232934890","freezeBlance":0.11,"availableBalance":93789.88}
         * content : 我发了第一条提问
         * goods_id : 6
         */

        private String query_id;
        private String user_id;
        private int praise_num;
        private int discuss_num;
        private long add_time;
        private UserBeanX user;
        private String content;
        private String goods_id;
        private String goods_name;
        private int teacher_reply;

        public int getTeacher_reply() {
            return teacher_reply;
        }

        public void setTeacher_reply(int teacher_reply) {
            this.teacher_reply = teacher_reply;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
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

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
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

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public static class UserBeanX implements Serializable {
            /**
             * img_name : 42fb9d5307504ec9bf1177dae0d542ed.png
             * user_id : 32666
             * img_path : uploadFiles/uploadImgs/32666/
             * nickName : Vincent
             * userName : 13232934890
             * freezeBlance : 0.11
             * availableBalance : 93789.88
             */

            private String img_name;
            private String user_id;
            private String img_path;
            private String nickName;
            private String userName;
            private double freezeBlance;
            private double availableBalance;

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

            public double getFreezeBlance() {
                return freezeBlance;
            }

            public void setFreezeBlance(double freezeBlance) {
                this.freezeBlance = freezeBlance;
            }

            public double getAvailableBalance() {
                return availableBalance;
            }

            public void setAvailableBalance(double availableBalance) {
                this.availableBalance = availableBalance;
            }
        }
    }

    public static class AdvertListBean implements Serializable {
        /**
         * path : uploadFiles/uploadImgs/1/
         * name : 0d1dfa767999425ab93bbfa5104dd089.jpeg
         * accessory_id : c97282b1384345b98fe3ff231aac76c2
         * ad_title : 首页幻灯广告1
         * advert_id : 1
         * ad_ap_id : 262157
         */

        private String path;
        private String name;
        private String accessory_id;
        private String ad_title;
        private String advert_id;
        private String ad_ap_id;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccessory_id() {
            return accessory_id;
        }

        public void setAccessory_id(String accessory_id) {
            this.accessory_id = accessory_id;
        }

        public String getAd_title() {
            return ad_title;
        }

        public void setAd_title(String ad_title) {
            this.ad_title = ad_title;
        }

        public String getAdvert_id() {
            return advert_id;
        }

        public void setAdvert_id(String advert_id) {
            this.advert_id = advert_id;
        }

        public String getAd_ap_id() {
            return ad_ap_id;
        }

        public void setAd_ap_id(String ad_ap_id) {
            this.ad_ap_id = ad_ap_id;
        }
    }

    public static class MonthresultBean implements Serializable {
        /**
         * user_id : 214d1d1f759049789abfedef365cd5f2
         * monthresult_id : 253aad791ce94ee19fba4520c6bcd5c5
         * title : 2017江门市安信职业安全培训有限公司安全生产管理人员危险化学品经营单位初训17期次补考成绩单
         * createdtime : 2018-02-07 17:18:58
         * content : [{"name":"余月仪","card_num":"201700007385","bishi":"76.0","jishi":""},{"name":"刘志红","card_num":"201700007389","bishi":"0.0","jishi":""},{"name":"彭丽薇","card_num":"201700007400","bishi":"0.0","jishi":""},{"name":"方秀云","card_num":"201700007401","bishi":"77.0","jishi":""},{"name":"梁伟杰","card_num":"201700007416","bishi":"76.0","jishi":""},{"name":"罗海荣","card_num":"201700007428","bishi":"0.0","jishi":""},{"name":"陈洁华","card_num":"201700007442","bishi":"0.0","jishi":""},{"name":"董循天","card_num":"201700007432","bishi":"95.0","jishi":""},{"name":"江国兵","card_num":"201700007418","bishi":"0.0","jishi":""},{"name":"陈伟乐","card_num":"201700007440","bishi":"62.0","jishi":""},{"name":"胡贺威","card_num":"201700007431","bishi":"71.0","jishi":""},{"name":"黄晓荣","card_num":"201700007449","bishi":"83.0","jishi":""},{"name":"甘锦桥","card_num":"201700007424","bishi":"69.0","jishi":""},{"name":"李伯成","card_num":"201700007404","bishi":"0.0","jishi":""},{"name":"何史彬","card_num":"201700007382","bishi":"0.0","jishi":""},{"name":"雷淑媛","card_num":"201700007445","bishi":"75.0","jishi":""},{"name":"李晓云","card_num":"201700007405","bishi":"66.0","jishi":""},{"name":"聂世铭","card_num":"201700007429","bishi":"74.0","jishi":""},{"name":"容国斌","card_num":"201700007392","bishi":"76.0","jishi":""},{"name":"黄哲聪","card_num":"201700007448","bishi":"70.0","jishi":""},{"name":"蔡慕柳","card_num":"201700007434","bishi":"90.0","jishi":""}]
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

        public static class ContentBean implements Serializable {
            /**
             * name : 余月仪
             * card_num : 201700007385
             * bishi : 76.0
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

    public static class TeacherListBean implements Serializable {
        /**
         * zip : 529300
         * profession : java开发
         * teacher_name : 周老师
         * id_number : 440215122512362234
         * address : 广东省江门市蓬江区群华路2号3栋
         * education : 大专
         * teacher_id : 2
         * telephone : 2326547
         * source : 网上
         * school_max : 北大青鸟
         * img_name : 78c7f49ab5b94b109ed3f4bcf0cb4caa.jpg
         * user_id : 32904
         * school : 北大青鸟
         * phone : 13422144236
         * img_path : uploadFiles/uploadImgs/32904/
         * bz : 备注备注
         * id_type : 身份证
         * position : javac开发工程师
         * state : 1
         * add_time : 1515398667000
         * user : {"img_name":"78c7f49ab5b94b109ed3f4bcf0cb4caa.jpg","user_id":"32904","img_path":"uploadFiles/uploadImgs/32904/","name":"美的店主","userName":"123123"}
         */

        private String zip;
        private String profession;
        private String teacher_name;
        private String id_number;
        private String address;
        private String education;
        private String teacher_id;
        private String telephone;
        private String source;
        private String school_max;
        private String img_name;
        private String user_id;
        private String school;
        private String phone;
        private String img_path;
        private String bz;
        private String id_type;
        private String position;
        private String state;
        private long add_time;
        private UserBean user;

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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public static class UserBean implements Serializable {
            /**
             * img_name : 78c7f49ab5b94b109ed3f4bcf0cb4caa.jpg
             * user_id : 32904
             * img_path : uploadFiles/uploadImgs/32904/
             * name : 美的店主
             * userName : 123123
             */

            private String img_name;
            private String user_id;
            private String img_path;
            private String name;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
