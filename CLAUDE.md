# CLAUDE.md - AI Assistant Guide

This document provides comprehensive guidance for AI assistants (like Claude) working with this codebase. It outlines the repository structure, development workflows, coding conventions, and best practices to ensure consistent and high-quality contributions.

## Table of Contents

1. [Repository Overview](#repository-overview)
2. [Codebase Structure](#codebase-structure)
3. [Development Workflow](#development-workflow)
4. [Coding Conventions](#coding-conventions)
5. [Testing Guidelines](#testing-guidelines)
6. [Documentation Standards](#documentation-standards)
7. [Git Workflow](#git-workflow)
8. [Key Patterns and Practices](#key-patterns-and-practices)
9. [Common Tasks](#common-tasks)
10. [Troubleshooting](#troubleshooting)

---

## Repository Overview

### Purpose
This repository serves as [describe the primary purpose of your repository].

### Tech Stack
- **Language(s)**: [e.g., TypeScript, Python, Go]
- **Framework(s)**: [e.g., React, Express, Django]
- **Build Tools**: [e.g., Webpack, Vite, npm]
- **Testing**: [e.g., Jest, Pytest, Mocha]
- **Linting/Formatting**: [e.g., ESLint, Prettier, Black]

### Key Dependencies
[List major dependencies and their purposes]

---

## Codebase Structure

```
.
├── src/                    # Source code
│   ├── components/         # Reusable components
│   ├── services/          # Business logic and services
│   ├── utils/             # Utility functions
│   ├── types/             # Type definitions
│   └── config/            # Configuration files
├── tests/                 # Test files
│   ├── unit/              # Unit tests
│   ├── integration/       # Integration tests
│   └── e2e/               # End-to-end tests
├── docs/                  # Documentation
├── scripts/               # Build and deployment scripts
├── .github/               # GitHub-specific files (workflows, templates)
└── public/                # Static assets
```

### Key Directories

- **`src/`**: Main application code
  - Follow modular architecture
  - Each module should have clear responsibilities
  - Avoid circular dependencies

- **`tests/`**: All test files
  - Mirror the structure of `src/`
  - Test files should be named `*.test.*` or `*.spec.*`

- **`docs/`**: Additional documentation
  - API documentation
  - Architecture decision records (ADRs)
  - Guides and tutorials

---

## Development Workflow

### Setting Up the Development Environment

1. **Clone the repository**
   ```bash
   git clone [repository-url]
   cd [repository-name]
   ```

2. **Install dependencies**
   ```bash
   npm install  # or yarn install, pip install -r requirements.txt, etc.
   ```

3. **Set up environment variables**
   ```bash
   cp .env.example .env
   # Edit .env with appropriate values
   ```

4. **Run the development server**
   ```bash
   npm run dev  # or appropriate command
   ```

### Before Making Changes

1. **Pull latest changes**
   ```bash
   git pull origin main
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/descriptive-name
   # or fix/bug-description
   # or refactor/component-name
   ```

3. **Understand the context**
   - Review related code
   - Check existing tests
   - Read relevant documentation

### Making Changes

1. **Write code following conventions** (see [Coding Conventions](#coding-conventions))

2. **Write/update tests**
   - Add unit tests for new functions
   - Update integration tests if behavior changes
   - Ensure all tests pass

3. **Update documentation**
   - Update inline comments
   - Update README if needed
   - Add/update API documentation

4. **Run linters and formatters**
   ```bash
   npm run lint
   npm run format
   ```

5. **Test your changes**
   ```bash
   npm test
   npm run test:integration
   npm run build
   ```

---

## Coding Conventions

### General Principles

1. **Write clean, readable code**
   - Use descriptive variable and function names
   - Keep functions small and focused
   - Avoid deep nesting (max 3-4 levels)

2. **Follow DRY (Don't Repeat Yourself)**
   - Extract common logic into reusable functions
   - Use constants for repeated values

3. **SOLID principles**
   - Single Responsibility Principle
   - Open/Closed Principle
   - Liskov Substitution Principle
   - Interface Segregation Principle
   - Dependency Inversion Principle

### Language-Specific Conventions

#### TypeScript/JavaScript

- Use **TypeScript** for type safety
- Prefer `const` over `let`, avoid `var`
- Use arrow functions for callbacks
- Use async/await over promises chains
- Use template literals for string interpolation
- Destructure objects and arrays where appropriate

```typescript
// Good
const getUserData = async (userId: string): Promise<User> => {
  const response = await fetch(`/api/users/${userId}`);
  return response.json();
};

// Avoid
function getUserData(userId) {
  return fetch('/api/users/' + userId).then(function(response) {
    return response.json();
  });
}
```

#### Naming Conventions

- **Variables/Functions**: `camelCase`
- **Classes/Types**: `PascalCase`
- **Constants**: `UPPER_SNAKE_CASE`
- **Private members**: prefix with `_` (if not using TypeScript private)
- **Boolean variables**: prefix with `is`, `has`, `should`, `can`

```typescript
const MAX_RETRY_COUNT = 3;
const isUserAuthenticated = true;
const hasPermission = checkPermission(user);

class UserService {
  private _cache: Map<string, User>;

  async getUser(id: string): Promise<User> {
    // ...
  }
}
```

### File Organization

- One component/class per file
- Group related files in directories
- Use index files for clean imports
- Keep files under 300 lines (guideline, not strict rule)

### Comments and Documentation

- **When to comment**:
  - Complex algorithms
  - Non-obvious business logic
  - API contracts
  - TODOs and FIXMEs (with context and assignee)

- **When NOT to comment**:
  - Obvious code
  - Instead of writing clear code

```typescript
// Good - explains WHY
// We retry up to 3 times because the external API is occasionally flaky
const maxRetries = 3;

// Bad - explains WHAT (code already shows this)
// Set maxRetries to 3
const maxRetries = 3;
```

### Error Handling

- Always handle errors explicitly
- Use try-catch for async operations
- Provide meaningful error messages
- Log errors with context

```typescript
try {
  const user = await getUserData(userId);
  return user;
} catch (error) {
  logger.error('Failed to fetch user data', { userId, error });
  throw new UserNotFoundError(`User ${userId} not found`);
}
```

---

## Testing Guidelines

### Test Structure

Follow the **Arrange-Act-Assert** pattern:

```typescript
describe('UserService', () => {
  describe('getUser', () => {
    it('should return user when user exists', async () => {
      // Arrange
      const userId = '123';
      const expectedUser = { id: userId, name: 'John' };
      mockDatabase.setup(/* ... */);

      // Act
      const result = await userService.getUser(userId);

      // Assert
      expect(result).toEqual(expectedUser);
    });
  });
});
```

### Test Coverage

- **Aim for >80% coverage** for critical paths
- Test edge cases and error conditions
- Don't test implementation details
- Focus on behavior, not internals

### Types of Tests

1. **Unit Tests**
   - Test individual functions/methods
   - Mock dependencies
   - Fast and isolated

2. **Integration Tests**
   - Test multiple components together
   - Use real dependencies where practical
   - Test API endpoints, database interactions

3. **End-to-End Tests**
   - Test complete user workflows
   - Run against staging environment
   - Fewer but more comprehensive

### Test Best Practices

- Tests should be independent (no shared state)
- Use descriptive test names
- One assertion per test (when practical)
- Clean up after tests (teardown)

---

## Documentation Standards

### Code Documentation

1. **JSDoc/TypeDoc** for public APIs:
   ```typescript
   /**
    * Fetches user data from the database
    * @param userId - The unique identifier for the user
    * @returns Promise resolving to User object
    * @throws {UserNotFoundError} When user doesn't exist
    */
   async function getUser(userId: string): Promise<User> {
     // ...
   }
   ```

2. **Inline comments** for complex logic:
   - Explain WHY, not WHAT
   - Keep comments up-to-date with code
   - Remove commented-out code

### README Guidelines

Every major module/package should have a README with:
- Purpose and overview
- Installation instructions
- Usage examples
- API documentation (or link to it)
- Configuration options

### Architecture Decision Records (ADRs)

For significant architectural decisions, create an ADR in `docs/adr/`:
- Context and problem statement
- Considered options
- Decision and rationale
- Consequences

---

## Git Workflow

### Branch Naming

- `feature/description` - New features
- `fix/description` - Bug fixes
- `refactor/description` - Code refactoring
- `docs/description` - Documentation updates
- `test/description` - Test additions/updates
- `chore/description` - Maintenance tasks

### Commit Messages

Follow the **Conventional Commits** format:

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types**:
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting)
- `refactor`: Code refactoring
- `test`: Adding/updating tests
- `chore`: Maintenance tasks

**Example**:
```
feat(auth): add OAuth2 authentication

Implement OAuth2 flow for Google and GitHub providers.
Includes token refresh logic and session management.

Closes #123
```

### Commit Best Practices

- Make atomic commits (one logical change per commit)
- Write clear, descriptive commit messages
- Commit often, push regularly
- Don't commit sensitive data or credentials

### Pull Request Process

1. **Create PR with descriptive title and description**
   - Explain WHAT and WHY
   - Link related issues
   - Add screenshots for UI changes

2. **PR Description Template**:
   ```markdown
   ## Summary
   Brief description of changes

   ## Changes
   - Change 1
   - Change 2

   ## Testing
   - [ ] Unit tests added/updated
   - [ ] Integration tests pass
   - [ ] Manually tested

   ## Screenshots (if applicable)

   ## Related Issues
   Closes #123
   ```

3. **Code Review**
   - Address all review comments
   - Keep discussions professional
   - Don't take feedback personally

4. **Merging**
   - Ensure CI/CD passes
   - Get required approvals
   - Squash commits if needed
   - Delete branch after merge

---

## Key Patterns and Practices

### For AI Assistants

When working with this codebase as an AI assistant:

1. **Always Read Before Writing**
   - Read existing files before making changes
   - Understand the context and patterns
   - Follow established conventions

2. **Preserve Existing Patterns**
   - Match the coding style of surrounding code
   - Use the same patterns for similar functionality
   - Don't introduce new patterns without justification

3. **Be Explicit About Changes**
   - Clearly explain what you're changing and why
   - Highlight any breaking changes
   - Note any assumptions you're making

4. **Test Your Changes**
   - Run existing tests
   - Add new tests for new functionality
   - Verify the build succeeds

5. **Update Documentation**
   - Keep inline comments up-to-date
   - Update README if behavior changes
   - Add documentation for new features

6. **Handle Errors Gracefully**
   - Never ignore errors
   - Provide helpful error messages
   - Log errors with context

7. **Security Considerations**
   - Never commit secrets or credentials
   - Validate and sanitize user input
   - Be aware of common vulnerabilities (XSS, SQL injection, etc.)
   - Use parameterized queries for databases
   - Implement proper authentication and authorization

8. **Performance Considerations**
   - Avoid N+1 queries
   - Use appropriate data structures
   - Cache when appropriate
   - Optimize critical paths

9. **Make Incremental Changes**
   - Small, focused changes are better
   - Easier to review and test
   - Easier to rollback if needed

10. **Ask When Uncertain**
    - If requirements are unclear, ask
    - If multiple approaches exist, discuss
    - Don't make assumptions about user intent

---

## Common Tasks

### Adding a New Feature

1. Create feature branch
2. Implement feature with tests
3. Update documentation
4. Run linters and tests
5. Create pull request

### Fixing a Bug

1. Write a failing test that reproduces the bug
2. Fix the bug
3. Verify the test passes
4. Add regression test if needed
5. Update changelog

### Refactoring Code

1. Ensure existing tests pass
2. Make incremental refactoring changes
3. Run tests after each change
4. Don't change behavior (tests should still pass)
5. Update documentation if interfaces change

### Updating Dependencies

1. Check changelog for breaking changes
2. Update dependency version
3. Run tests to catch issues
4. Update code if needed for breaking changes
5. Test thoroughly

---

## Troubleshooting

### Common Issues

#### Build Failures

1. Clear cache: `npm clean-install`
2. Check Node version matches requirements
3. Verify environment variables are set
4. Check for TypeScript errors: `npm run type-check`

#### Test Failures

1. Run tests in isolation
2. Check for timing issues (async operations)
3. Verify test data and mocks
4. Clear test caches

#### Linting Errors

1. Run auto-fix: `npm run lint:fix`
2. Check for conflicting rules
3. Update ESLint configuration if needed

### Getting Help

- Check existing documentation
- Search closed issues and PRs
- Ask in team chat/discussions
- Create an issue with:
  - Clear description
  - Steps to reproduce
  - Expected vs actual behavior
  - Environment details

---

## Additional Resources

- [Project Documentation](./docs/)
- [API Reference](./docs/api/)
- [Contributing Guide](./CONTRIBUTING.md)
- [Code of Conduct](./CODE_OF_CONDUCT.md)
- [Changelog](./CHANGELOG.md)

---

## Maintenance

This CLAUDE.md file should be updated when:
- Project structure changes significantly
- New conventions are adopted
- Development workflow changes
- New tools or dependencies are added
- Common issues and solutions are discovered

**Last Updated**: 2025-11-18

**Maintained By**: Development Team
