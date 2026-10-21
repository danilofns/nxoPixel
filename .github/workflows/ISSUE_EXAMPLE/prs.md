# feat: add named player homes

## Summary

Implements a home management system that allows players to save named locations and teleport back to them using commands.

## Motivation

Players currently have no built-in way to save multiple locations for later use. This feature improves server usability by providing a persistent home system.

## Changes

- Adds the `/sethome <name>` command to save a player's current location.
- Adds the `/home <name>` command to teleport to a saved home.
- Introduces a `homeManager` to centralize home management logic.
- Persists player homes in a JSON file.
- Adds validation for missing names and nonexistent homes.

## Implementation details

- **Language:** Java 21
- **Platform:** Minecraft 1.21.1
- **Persistence:** JSON
- **Architecture:** Separates command handling from home management logic.

## Testing

- [ ] Verify that players can save multiple named homes.
- [ ] Verify that `/home <name>` teleports players to the correct location.
- [ ] Verify that homes persist after a server restart.
- [ ] Verify that invalid home names are handled correctly.
- [ ] Verify that players cannot access another player's private homes.

## Compatibility

- Minecraft 1.21.1
- Java 21
- Compatible server software and dependencies must be installed.

## Checklist

- [ ] Code follows the project's conventions.
- [ ] No unrelated files or changes are included.
- [ ] Tests have been executed successfully.
- [ ] Documentation has been updated where necessary.

## Related issues

Closes #ISSUE_NUMBER
