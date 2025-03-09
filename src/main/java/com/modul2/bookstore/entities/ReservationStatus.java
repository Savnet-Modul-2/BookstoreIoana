package com.modul2.bookstore.entities;

public enum ReservationStatus {
    PENDING {
        @Override
        public boolean isNextStatePossible(ReservationStatus status) {
            return status == IN_PROGRESS || status == CANCELED;
        }
    },
    IN_PROGRESS {
        @Override
        public boolean isNextStatePossible(ReservationStatus status) {
            return status == FINISHED || status == DELAYED;
        }

    },
    DELAYED {
        @Override
        public boolean isNextStatePossible(ReservationStatus status) {
            return status == FINISHED;
        }
    },
    FINISHED {
        @Override
        public boolean isNextStatePossible(ReservationStatus status) {
            return false;
        }
    },
    CANCELED {
        @Override
        public boolean isNextStatePossible(ReservationStatus status) {
            return false;
        }
    };

    public abstract boolean isNextStatePossible(ReservationStatus status);
}
