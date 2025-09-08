-- Minimal schema (JPA will manage updates). Provided for initial dump.
CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(190) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS jobs (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  description TEXT,
  location VARCHAR(150),
  company VARCHAR(150),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  employer_id BIGINT,
  CONSTRAINT fk_jobs_employer FOREIGN KEY (employer_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS applications (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  status VARCHAR(30) NOT NULL,
  cover_letter TEXT,
  applicant_id BIGINT NOT NULL,
  job_id BIGINT NOT NULL,
  applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_app_user FOREIGN KEY (applicant_id) REFERENCES users(id),
  CONSTRAINT fk_app_job FOREIGN KEY (job_id) REFERENCES jobs(id),
  CONSTRAINT uc_app UNIQUE (applicant_id, job_id)
);
