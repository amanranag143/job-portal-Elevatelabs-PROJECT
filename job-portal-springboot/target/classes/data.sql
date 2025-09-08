-- BCrypt for password: password
-- $2a$10$k9g4q3.uYExnJ2cZyCq1ce4eW0p7DbmTfhXKQ8y9q3b8NjG1Yc4tu (placeholder; actual BCrypt at runtime)
-- We'll insert with plain text then app bootstraps encoder on first login/registration.
INSERT INTO users (email, password, name, role) VALUES
('employer@example.com', '{noop}password', 'Acme HR', 'EMPLOYER'),
('applicant@example.com', '{noop}password', 'Rana Applicant', 'APPLICANT');

INSERT INTO jobs (title, description, location, company, employer_id) VALUES
('Java Developer', 'Build REST APIs with Spring Boot.', 'Noida', 'Acme Corp', 1),
('Frontend Engineer', 'Work with React/TypeScript.', 'Remote', 'Acme Corp', 1);
