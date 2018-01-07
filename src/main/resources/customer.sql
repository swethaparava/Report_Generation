/*
 Navicat PostgreSQL Data Transfer

 Source Server         : localhost_5432
 Source Server Type    : PostgreSQL
 Source Server Version : 90510
 Source Host           : localhost:5432
 Source Catalog        : customer
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90510
 File Encoding         : 65001

 Date: 07/01/2018 22:45:09
*/


-- ----------------------------
-- Table structure for customerdetails
-- ----------------------------
DROP TABLE IF EXISTS "public"."customerdetails";
CREATE TABLE "public"."customerdetails" (
  "id" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of customerdetails
-- ----------------------------
INSERT INTO "public"."customerdetails" VALUES (1, 'swetha');

-- ----------------------------
-- Primary Key structure for table customerdetails
-- ----------------------------
ALTER TABLE "public"."customerdetails" ADD CONSTRAINT "customerdetails_pkey" PRIMARY KEY ("id");
