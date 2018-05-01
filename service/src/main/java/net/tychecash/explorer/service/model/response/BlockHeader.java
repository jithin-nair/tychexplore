/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.tychecash.explorer.service.model.response;

import net.tychecash.explorer.service.util.BlockUtil;

/**
 *
 * @author jithin
 */
public class BlockHeader {

    private String timestamp;

    private String orphan_status;

    private String minor_version;

    private Integer height;

    private String reward;

    private String nonce;

    private String hash;

    private String major_version;

    private String difficulty;

    private String depth;

    private String prev_hash;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrphan_status() {
        return orphan_status;
    }

    public void setOrphan_status(String orphan_status) {
        this.orphan_status = orphan_status;
    }

    public String getMinor_version() {
        return minor_version;
    }

    public void setMinor_version(String minor_version) {
        this.minor_version = minor_version;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = BlockUtil.insertCharAt(reward, '.', 8);
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMajor_version() {
        return major_version;
    }

    public void setMajor_version(String major_version) {
        this.major_version = major_version;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getPrev_hash() {
        return prev_hash;
    }

    public void setPrev_hash(String prev_hash) {
        this.prev_hash = prev_hash;
    }

    @Override
    public String toString() {
        return "BlockHeader [timestamp = " + timestamp + ", orphan_status = " + orphan_status + ", minor_version = " + minor_version + ", height = " + height + ", reward = " + reward + ", nonce = " + nonce + ", hash = " + hash + ", major_version = " + major_version + ", difficulty = " + difficulty + ", depth = " + depth + ", prev_hash = " + prev_hash + "]";
    }
}
