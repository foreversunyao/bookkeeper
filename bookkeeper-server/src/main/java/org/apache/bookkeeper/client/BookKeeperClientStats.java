/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.apache.bookkeeper.client;

import org.apache.bookkeeper.stats.Counter;
import org.apache.bookkeeper.stats.Gauge;
import org.apache.bookkeeper.stats.OpStatsLogger;
import org.apache.bookkeeper.stats.StatsLogger;

/**
 * List of constants for defining client stats names.
 */
public interface BookKeeperClientStats {
    String CLIENT_SCOPE = "bookkeeper_client";

    // Metadata Operations

    String CREATE_OP = "LEDGER_CREATE";
    String DELETE_OP = "LEDGER_DELETE";
    String OPEN_OP = "LEDGER_OPEN";
    String RECOVER_OP = "LEDGER_RECOVER";
    String LEDGER_RECOVER_READ_ENTRIES = "LEDGER_RECOVER_READ_ENTRIES";
    String LEDGER_RECOVER_ADD_ENTRIES = "LEDGER_RECOVER_ADD_ENTRIES";
    String LEDGER_ENSEMBLE_BOOKIE_DISTRIBUTION = "LEDGER_ENSEMBLE_BOOKIE_DISTRIBUTION";

    // Data Operations

    String ADD_OP = "ADD_ENTRY";
    String ADD_OP_UR = "ADD_ENTRY_UR"; // Under Replicated during AddEntry.
    String FORCE_OP = "FORCE"; // Number of force ledger operations
    String READ_OP = "READ_ENTRY";
    // Corrupted entry (Digest Mismatch/ Under Replication) detected during ReadEntry
    String READ_OP_DM = "READ_ENTRY_DM";
    String WRITE_LAC_OP = "WRITE_LAC";
    String READ_LAC_OP = "READ_LAC";
    String READ_LAST_CONFIRMED_AND_ENTRY = "READ_LAST_CONFIRMED_AND_ENTRY";
    String READ_LAST_CONFIRMED_AND_ENTRY_RESPONSE = "READ_LAST_CONFIRMED_AND_ENTRY_RESPONSE";
    String PENDING_ADDS = "NUM_PENDING_ADD";
    String ENSEMBLE_CHANGES = "NUM_ENSEMBLE_CHANGE";
    String LAC_UPDATE_HITS = "LAC_UPDATE_HITS";
    String LAC_UPDATE_MISSES = "LAC_UPDATE_MISSES";
    String GET_BOOKIE_INFO_OP = "GET_BOOKIE_INFO";
    String SPECULATIVE_READ_COUNT = "SPECULATIVE_READ_COUNT";
    String READ_REQUESTS_REORDERED = "READ_REQUESTS_REORDERED";

    // per channel stats
    String CHANNEL_SCOPE = "per_channel_bookie_client";

    String CHANNEL_READ_OP = "READ_ENTRY";
    String CHANNEL_TIMEOUT_READ = "TIMEOUT_READ_ENTRY";
    String CHANNEL_ADD_OP = "ADD_ENTRY";
    String CHANNEL_TIMEOUT_ADD = "TIMEOUT_ADD_ENTRY";
    String CHANNEL_WRITE_LAC_OP = "WRITE_LAC";
    String CHANNEL_FORCE_OP = "FORCE";
    String CHANNEL_TIMEOUT_WRITE_LAC = "TIMEOUT_WRITE_LAC";
    String CHANNEL_TIMEOUT_FORCE = "TIMEOUT_FORCE";
    String CHANNEL_READ_LAC_OP = "READ_LAC";
    String CHANNEL_TIMEOUT_READ_LAC = "TIMEOUT_READ_LAC";
    String TIMEOUT_GET_BOOKIE_INFO = "TIMEOUT_GET_BOOKIE_INFO";
    String CHANNEL_START_TLS_OP = "START_TLS";
    String CHANNEL_TIMEOUT_START_TLS_OP = "TIMEOUT_START_TLS";

    String NETTY_EXCEPTION_CNT = "NETTY_EXCEPTION_CNT";
    String CLIENT_CHANNEL_WRITE_WAIT = "CLIENT_CHANNEL_WRITE_WAIT";
    String CLIENT_CONNECT_TIMER = "CLIENT_CONNECT_TIMER";
    String ADD_OP_OUTSTANDING = "ADD_OP_OUTSTANDING";
    String READ_OP_OUTSTANDING = "READ_OP_OUTSTANDING";
    String NETTY_OPS = "NETTY_OPS";
    String ACTIVE_NON_TLS_CHANNEL_COUNTER = "ACTIVE_NON_TLS_CHANNEL_COUNTER";
    String ACTIVE_TLS_CHANNEL_COUNTER = "ACTIVE_TLS_CHANNEL_COUNTER";
    String FAILED_CONNECTION_COUNTER = "FAILED_CONNECTION_COUNTER";
    String FAILED_TLS_HANDSHAKE_COUNTER = "FAILED_TLS_HANDSHAKE_COUNTER";

    OpStatsLogger getCreateOpLogger();
    OpStatsLogger getOpenOpLogger();
    OpStatsLogger getDeleteOpLogger();
    OpStatsLogger getRecoverOpLogger();
    OpStatsLogger getReadOpLogger();
    OpStatsLogger getReadLacAndEntryOpLogger();
    OpStatsLogger getReadLacAndEntryRespLogger();
    OpStatsLogger getAddOpLogger();
    OpStatsLogger getForceOpLogger();
    OpStatsLogger getWriteLacOpLogger();
    OpStatsLogger getReadLacOpLogger();
    OpStatsLogger getRecoverAddCountLogger();
    OpStatsLogger getRecoverReadCountLogger();
    Counter getReadOpDmCounter();
    Counter getAddOpUrCounter();
    Counter getSpeculativeReadCounter();
    Counter getEnsembleBookieDistributionCounter(String bookie);
    Counter getEnsembleChangeCounter();
    Counter getLacUpdateHitsCounter();
    Counter getLacUpdateMissesCounter();
    OpStatsLogger getClientChannelWriteWaitLogger();
    void registerPendingAddsGauge(Gauge<Integer> gauge);

    static BookKeeperClientStats newInstance(StatsLogger stats) {
        OpStatsLogger createOpLogger = stats.getOpStatsLogger(CREATE_OP);
        OpStatsLogger deleteOpLogger = stats.getOpStatsLogger(DELETE_OP);
        OpStatsLogger openOpLogger = stats.getOpStatsLogger(OPEN_OP);
        OpStatsLogger recoverOpLogger = stats.getOpStatsLogger(RECOVER_OP);
        OpStatsLogger readOpLogger = stats.getOpStatsLogger(READ_OP);
        Counter readOpDmCounter = stats.getCounter(READ_OP_DM);
        OpStatsLogger readLacAndEntryOpLogger = stats.getOpStatsLogger(READ_LAST_CONFIRMED_AND_ENTRY);
        OpStatsLogger readLacAndEntryRespLogger = stats.getOpStatsLogger(READ_LAST_CONFIRMED_AND_ENTRY_RESPONSE);
        OpStatsLogger addOpLogger = stats.getOpStatsLogger(ADD_OP);
        OpStatsLogger forceOpLogger = stats.getOpStatsLogger(FORCE_OP);
        Counter addOpUrCounter = stats.getCounter(ADD_OP_UR);
        OpStatsLogger writeLacOpLogger = stats.getOpStatsLogger(WRITE_LAC_OP);
        OpStatsLogger readLacOpLogger = stats.getOpStatsLogger(READ_LAC_OP);
        OpStatsLogger recoverAddEntriesStats = stats.getOpStatsLogger(LEDGER_RECOVER_ADD_ENTRIES);
        OpStatsLogger recoverReadEntriesStats = stats.getOpStatsLogger(LEDGER_RECOVER_READ_ENTRIES);

        Counter ensembleChangeCounter = stats.getCounter(ENSEMBLE_CHANGES);
        Counter lacUpdateHitsCounter = stats.getCounter(LAC_UPDATE_HITS);
        Counter lacUpdateMissesCounter = stats.getCounter(LAC_UPDATE_MISSES);
        OpStatsLogger clientChannelWriteWaitStats = stats.getOpStatsLogger(CLIENT_CHANNEL_WRITE_WAIT);

        Counter speculativeReadCounter = stats.getCounter(SPECULATIVE_READ_COUNT);

        return new BookKeeperClientStats() {
            @Override
            public OpStatsLogger getCreateOpLogger() {
                return createOpLogger;
            }
            @Override
            public OpStatsLogger getOpenOpLogger() {
                return openOpLogger;
            }
            @Override
            public OpStatsLogger getDeleteOpLogger() {
                return deleteOpLogger;
            }
            @Override
            public OpStatsLogger getRecoverOpLogger() {
                return recoverOpLogger;
            }
            @Override
            public OpStatsLogger getReadOpLogger() {
                return readOpLogger;
            }
            @Override
            public OpStatsLogger getReadLacAndEntryOpLogger() {
                return readLacAndEntryOpLogger;
            }
            @Override
            public OpStatsLogger getReadLacAndEntryRespLogger() {
                return readLacAndEntryRespLogger;
            }
            @Override
            public OpStatsLogger getAddOpLogger() {
                return addOpLogger;
            }
            @Override
            public OpStatsLogger getForceOpLogger() {
                return forceOpLogger;
            }
            @Override
            public OpStatsLogger getWriteLacOpLogger() {
                return writeLacOpLogger;
            }
            @Override
            public OpStatsLogger getReadLacOpLogger() {
                return readLacOpLogger;
            }
            @Override
            public OpStatsLogger getRecoverAddCountLogger() {
                return recoverAddEntriesStats;
            }
            @Override
            public OpStatsLogger getRecoverReadCountLogger() {
                return recoverReadEntriesStats;
            }
            @Override
            public Counter getReadOpDmCounter() {
                return readOpDmCounter;
            }
            @Override
            public Counter getAddOpUrCounter() {
                return addOpUrCounter;
            }
            @Override
            public Counter getSpeculativeReadCounter() {
                return speculativeReadCounter;
            }
            @Override
            public Counter getEnsembleChangeCounter() {
                return ensembleChangeCounter;
            }
            @Override
            public Counter getLacUpdateHitsCounter() {
                return lacUpdateHitsCounter;
            }
            @Override
            public Counter getLacUpdateMissesCounter() {
                return lacUpdateMissesCounter;
            }
            @Override
            public OpStatsLogger getClientChannelWriteWaitLogger() {
                return clientChannelWriteWaitStats;
            }
            @Override
            public Counter getEnsembleBookieDistributionCounter(String bookie) {
                return stats.getCounter(LEDGER_ENSEMBLE_BOOKIE_DISTRIBUTION + "-" + bookie);
            }
            @Override
            public void registerPendingAddsGauge(Gauge<Integer> gauge) {
                stats.registerGauge(PENDING_ADDS, gauge);
            }
        };
    }

}
