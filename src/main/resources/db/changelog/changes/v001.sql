DROP TABLE IF EXISTS campaign_group;
CREATE TABLE campaign_group
(
    campaign_group_id       UUID    NOT NULL,
    campaign_group_name     VARCHAR(255) UNIQUE NOT NULL,
    measurement_consumer_id BIGINT,
    event_group_ids         VARCHAR(255) NOT NULL,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP,
    PRIMARY KEY (campaign_group_id)
);
