package com.freemud.app.mvp.demo.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by li.liu on 2017/6/20.
 */

public class CartGoodsJson {
    /**
     * count
     * linkId : 178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040
     * products : [{"barcode":"","category":"","categoryName":"","companyId":"2","customerCode":"","finalPrice":12,"labelNames":[{"lid":2,"name":"即时送","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":1}],"name":"商品3","originalPrice":12,"picture":"http://a2.qpic.cn/psb?/V11lkcWO1ezREK/WhDWS6w2rEzwa6*.miSlSM2Mp9DBVDplYU94eF4rkPo!/c/dD0BAAAAAAAA&ek=1&kp=1&pt=0&bo=LAEsASwBLAERADc!&tm=1498176000&sce=0-12-12&rf=0-18","pid":"3","productNumber":3,"saleCount":"","sellTimeName":"","specification":"","status":"2","type":2,"unit":""},{"barcode":"","category":"","categoryName":"","companyId":"2","customerCode":"","finalPrice":12,"labelNames":[{"lid":2,"name":"即时送","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":1}],"name":"商品5","originalPrice":12,"picture":"http://a2.qpic.cn/psb?/V11lkcWO1ezREK/WhDWS6w2rEzwa6*.miSlSM2Mp9DBVDplYU94eF4rkPo!/c/dD0BAAAAAAAA&ek=1&kp=1&pt=0&bo=LAEsASwBLAERADc!&tm=1498176000&sce=0-12-12&rf=0-18","pid":"5","productNumber":5,"saleCount":"","sellTimeName":"","specification":"","status":"2","type":2,"unit":""},{"barcode":"","category":"","categoryName":"","companyId":"2","customerCode":"","finalPrice":12,"labelNames":[{"lid":2,"name":"即时送","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":1}],"name":"商品6","originalPrice":12,"picture":"http://a2.qpic.cn/psb?/V11lkcWO1ezREK/WhDWS6w2rEzwa6*.miSlSM2Mp9DBVDplYU94eF4rkPo!/c/dD0BAAAAAAAA&ek=1&kp=1&pt=0&bo=LAEsASwBLAERADc!&tm=1498176000&sce=0-12-12&rf=0-18","pid":"6","productNumber":6,"saleCount":"","sellTimeName":"","specification":"","status":"2","type":2,"unit":""},{"barcode":"","category":"","categoryName":"","companyId":"2","customerCode":"","finalPrice":12,"labelNames":[{"lid":2,"name":"即时送","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":1}],"name":"商品7","originalPrice":12,"picture":"http://a2.qpic.cn/psb?/V11lkcWO1ezREK/WhDWS6w2rEzwa6*.miSlSM2Mp9DBVDplYU94eF4rkPo!/c/dD0BAAAAAAAA&ek=1&kp=1&pt=0&bo=LAEsASwBLAERADc!&tm=1498176000&sce=0-12-12&rf=0-18","pid":"7","productNumber":7,"saleCount":"","sellTimeName":"","specification":"","status":"2","type":2,"unit":""},{"barcode":"","category":"","categoryName":"","companyId":"2","customerCode":"","finalPrice":12,"labelNames":[{"lid":2,"name":"即时送","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":1}],"name":"商品8","originalPrice":12,"picture":"http://a2.qpic.cn/psb?/V11lkcWO1ezREK/WhDWS6w2rEzwa6*.miSlSM2Mp9DBVDplYU94eF4rkPo!/c/dD0BAAAAAAAA&ek=1&kp=1&pt=0&bo=LAEsASwBLAERADc!&tm=1498176000&sce=0-12-12&rf=0-18","pid":"8","productNumber":8,"saleCount":"","sellTimeName":"","specification":"","status":"2","type":2,"unit":""},{"barcode":"","category":"","categoryName":"","companyId":"2","customerCode":"5648858","finalPrice":19,"labelNames":[{"lid":2,"name":"即时送","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":1}],"name":"鲜切哈密瓜","originalPrice":19,"picture":"http://a2.qpic.cn/psb?/V11lkcWO1ezREK/WhDWS6w2rEzwa6*.miSlSM2Mp9DBVDplYU94eF4rkPo!/c/dD0BAAAAAAAA&ek=1&kp=1&pt=0&bo=LAEsASwBLAERADc!&tm=1498176000&sce=0-12-12&rf=0-18","pid":"13","productNumber":13,"saleCount":11,"sellTimeName":"","specification":"350g","status":"2","type":2,"unit":"g"},{"barcode":"","category":"","categoryName":"","companyId":"2","customerCode":"8948564","finalPrice":19,"labelNames":[{"lid":1,"name":"备注","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":3},{"lid":2,"name":"即时送","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":1}],"name":"山东大枣","originalPrice":19,"picture":"http://a2.qpic.cn/psb?/V11lkcWO1ezREK/WhDWS6w2rEzwa6*.miSlSM2Mp9DBVDplYU94eF4rkPo!/c/dD0BAAAAAAAA&ek=1&kp=1&pt=0&bo=LAEsASwBLAERADc!&tm=1498176000&sce=0-12-12&rf=0-18","pid":"14","productNumber":14,"saleCount":11,"sellTimeName":"","specification":"350g","status":"2","type":2,"unit":"g"}]
     * scid : 78741807064678672
     * type : 1
     * userId : 6de4bcba-79a6-4bc5-94f0-d44a0a6bdf18
     */

    private String linkId;
    private long scid;
    private int type;
    private String userId;
    private List<ProductsBean> products;
    private int count;//数据总数


    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public long getScid() {
        return scid;
    }

    public void setScid(long scid) {
        this.scid = scid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean implements Parcelable{
        /**
         * barcode :
         * category :
         * categoryName ://判断是否是秒杀
         * companyId : 2
         * customerCode :
         * finalPrice : 12
         * labelNames : [{"lid":2,"name":"即时送","shopId":"178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040","type":1}]   type 1=商品标签,2=促销标签,3=备注标签,4=特殊标签
         *
         * name : 商品3
         * originalPrice : 12
         * picture : http://a2.qpic.cn/psb?/V11lkcWO1ezREK/WhDWS6w2rEzwa6*.miSlSM2Mp9DBVDplYU94eF4rkPo!/c/dD0BAAAAAAAA&ek=1&kp=1&pt=0&bo=LAEsASwBLAERADc!&tm=1498176000&sce=0-12-12&rf=0-18
         * pid : 3
         * productNumber : 3
         * saleCount :
         * sellTimeName :
         * specification :
         * status : 2
         * type : 2
         * unit :
         *
         * pCheck;//是否选中
         */

        private String barcode;
        private String category;
        private String categoryName;
        private String companyId;
        private String customerCode;
        private int finalPrice;
        private String name;
        private int originalPrice;
        private String picture;
        private String pid;
        private String remark;
        private int productNumber;
        private String saleCount;
        private String sellTimeName;
        private String specification;
        private String status;
        private int type;
        private String unit;
        private List<LabelNamesBean> labelNames;
        private boolean pCheck;

        public ProductsBean() {
        }


        protected ProductsBean(Parcel in) {
            barcode = in.readString();
            category = in.readString();
            categoryName = in.readString();
            companyId = in.readString();
            customerCode = in.readString();
            finalPrice = in.readInt();
            name = in.readString();
            originalPrice = in.readInt();
            picture = in.readString();
            pid = in.readString();
            remark = in.readString();
            productNumber = in.readInt();
            saleCount = in.readString();
            sellTimeName = in.readString();
            specification = in.readString();
            status = in.readString();
            type = in.readInt();
            unit = in.readString();
            labelNames = in.createTypedArrayList(LabelNamesBean.CREATOR);
            pCheck = in.readByte() != 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(barcode);
            dest.writeString(category);
            dest.writeString(categoryName);
            dest.writeString(companyId);
            dest.writeString(customerCode);
            dest.writeInt(finalPrice);
            dest.writeString(name);
            dest.writeInt(originalPrice);
            dest.writeString(picture);
            dest.writeString(pid);
            dest.writeString(remark);
            dest.writeInt(productNumber);
            dest.writeString(saleCount);
            dest.writeString(sellTimeName);
            dest.writeString(specification);
            dest.writeString(status);
            dest.writeInt(type);
            dest.writeString(unit);
            dest.writeTypedList(labelNames);
            dest.writeByte((byte) (pCheck ? 1 : 0));
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ProductsBean> CREATOR = new Creator<ProductsBean>() {
            @Override
            public ProductsBean createFromParcel(Parcel in) {
                return new ProductsBean(in);
            }

            @Override
            public ProductsBean[] newArray(int size) {
                return new ProductsBean[size];
            }
        };

        public boolean getpCheck() {
            return pCheck;
        }

        public void setpCheck(boolean pCheck) {
            this.pCheck = pCheck;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public int getFinalPrice() {
            return finalPrice;
        }

        public void setFinalPrice(int finalPrice) {
            this.finalPrice = finalPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public int getProductNumber() {
            return productNumber;
        }

        public void setProductNumber(int productNumber) {
            this.productNumber = productNumber;
        }

        public String getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(String saleCount) {
            this.saleCount = saleCount;
        }

        public String getSellTimeName() {
            return sellTimeName;
        }

        public void setSellTimeName(String sellTimeName) {
            this.sellTimeName = sellTimeName;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public List<LabelNamesBean> getLabelNames() {
            return labelNames;
        }

        public void setLabelNames(List<LabelNamesBean> labelNames) {
            this.labelNames = labelNames;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public static class LabelNamesBean implements Parcelable{
            /**
             * lid : 2
             * name : 即时送
             * shopId : 178a14ba-85a8-40c7-9ff4-6418418f5a0c_31310040
             * type : 1
             */

            private int lid;
            private String name;
            private String shopId;
            private int type;

            protected LabelNamesBean(Parcel in) {
                lid = in.readInt();
                name = in.readString();
                shopId = in.readString();
                type = in.readInt();
            }

            public static final Creator<LabelNamesBean> CREATOR = new Creator<LabelNamesBean>() {
                @Override
                public LabelNamesBean createFromParcel(Parcel in) {
                    return new LabelNamesBean(in);
                }

                @Override
                public LabelNamesBean[] newArray(int size) {
                    return new LabelNamesBean[size];
                }
            };

            public LabelNamesBean() {
            }

            public int getLid() {
                return lid;
            }

            public void setLid(int lid) {
                this.lid = lid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(lid);
                parcel.writeString(name);
                parcel.writeString(shopId);
                parcel.writeInt(type);
            }
        }
    }
}
