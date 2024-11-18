GENERAL
-
The goal of this project is to make a procedurally determined path.
By procedurally we mean that the code should generate a new path each match.
This also means that the code should change the path if things don't go to plan.

INPUT
-
- Shuffleboard priorities <sup>Sendable<sub>maybe</sub></sup>
- Commands <sup>Commands</sup>
- POI coordinates <sup>Translation2D</sup>
- Robot coordinates <sup>Translation2D</sup>
- Robot rotation <sup>Rotation2D</sup>
- camera data <sub>to decide</sub>
    - POI in view <sup>boolean</sup>
    - POI estimated coordinates <sup>Translation2D</sup>

OUTPUT
-
- Robot coordinates
- Robot rotation

DEV
-
- If you want to contribute to the project, please make a pull request for each commit.
- You may create a branch for significant issues. For example, making a branch for adding shuffleboard implementation.
