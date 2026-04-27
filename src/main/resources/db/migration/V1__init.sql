-- Annual leaves
CREATE TABLE annual_leaves
(
    id         UUID PRIMARY KEY,
    balance    INTEGER     NOT NULL CHECK (balance > 0),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ
);

-- Exceptional leaves
CREATE TABLE exceptional_leaves
(
    id            UUID PRIMARY KEY,
    name          varchar(50) NOT NULL UNIQUE,
    legal_balance INTEGER     NOT NULL CHECK (legal_balance >= 0),
    granted_days  INTEGER     NOT NULL CHECK (granted_days >= 0),
    created_at    TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMPTZ
);

-- Work schedules
CREATE TABLE work_schedules
(
    id          UUID PRIMARY KEY,
    type        VARCHAR(50) NOT NULL CHECK (type IN ('FIXED_HOURS', 'FLEXIBLE_HOURS')),
    entry_time  TIME,
    exit_time   TIME,
    daily_hours DECIMAL(4, 2),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMPTZ
);

-- Absence justifications
CREATE TABLE absence_justifications
(
    id                     UUID PRIMARY KEY,
    allowed_delay_in_hours INTEGER     NOT NULL CHECK (allowed_delay_in_hours > 0),
    created_at             TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at             TIMESTAMPTZ
);

-- Alert thresholds
CREATE TABLE alert_thresholds
(
    id                 UUID PRIMARY KEY,
    warning_threshold  INTEGER     NOT NULL CHECK (warning_threshold > 0),
    critical_threshold INTEGER     NOT NULL CHECK (critical_threshold > warning_threshold),
    created_at         TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMPTZ
);

-- Document templates
CREATE TABLE document_templates
(
    id         UUID PRIMARY KEY,
    category   varchar(50) NOT NULL CHECK (category IN
                                           ('ATTESTATION', 'CONTRACT', 'DISCIPLINARY', 'INTERNSHIP', 'POLICY')),
    path       TEXT        NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ
);

-- Recruitment sites
CREATE TABLE recruitment_sites
(
    id              UUID PRIMARY KEY,
    primary_color   VARCHAR(50)  NOT NULL,
    secondary_color VARCHAR(50)  NOT NULL,
    logo_path       TEXT         NOT NULL,
    description     TEXT         NOT NULL,
    founded_year    VARCHAR(4)   NOT NULL,
    email           VARCHAR(255) NOT NULL,
    phone           VARCHAR(20)  NOT NULL,
    address         VARCHAR(255) NOT NULL,
    linkedin_url    TEXT         NOT NULL,
    created_at      TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMPTZ
);