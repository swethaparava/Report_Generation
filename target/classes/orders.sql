/*
 Navicat PostgreSQL Data Transfer

 Source Server         : localhost_5432
 Source Server Type    : PostgreSQL
 Source Server Version : 90510
 Source Host           : localhost:5432
 Source Catalog        : orders
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90510
 File Encoding         : 65001

 Date: 07/01/2018 22:45:25
*/


-- ----------------------------
-- Table structure for orderhistory
-- ----------------------------
DROP TABLE IF EXISTS "public"."orderhistory";
CREATE TABLE "public"."orderhistory" (
  "id" int4 NOT NULL,
  "customerid" varchar(255) COLLATE "pg_catalog"."default",
  "orderdate" date
)
;

-- ----------------------------
-- Records of orderhistory
-- ----------------------------
INSERT INTO "public"."orderhistory" VALUES (100, '1', '2018-01-07');

-- ----------------------------
-- Primary Key structure for table orderhistory
-- ----------------------------
ALTER TABLE "public"."orderhistory" ADD CONSTRAINT "orderhistory_pkey" PRIMARY KEY ("id");
