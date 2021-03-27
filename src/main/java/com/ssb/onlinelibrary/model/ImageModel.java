package com.ssb.onlinelibrary.model;

import javax.persistence.*;
@Entity
@Table(name = "image_table")
public class ImageModel {
    public ImageModel() {
            super();
        }
        public ImageModel(String name, String type, byte[] picByte) {
            this.name = name;
            this.type = type;
            this.picByte = picByte;
        }
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "name")
        private String name;
        @Column(name = "type")
        private String type;

        @Column(name = "picByte", length = 1000)
        private byte[] picByte;

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        private User user;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public byte[] getPicByte() {
            return picByte;
        }
        public void setPicByte(byte[] picByte) {
            this.picByte = picByte;
        }
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
}






