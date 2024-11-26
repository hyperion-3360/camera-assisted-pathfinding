package frc.robot.Auto;

import java.util.LinkedList;
import java.util.function.BooleanSupplier;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathConstraints;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

/**
 * Pathfinding
 */
public class Pathfinding {
    public enum POI {
        Note1(0.0,0.0,0.0, () -> ConditionBuilder.intakeHasNote()),
        Note2(0.0,0.0,0.0, () -> false);

        private double x_coordinates;
        private double y_coordinates;
        private Rotation2d angle;
        private Translation2d xy_coordinates;
        private BooleanSupplier conditions;

        private POI(double x_coordinates, double y_coordinates,
         double angle, BooleanSupplier removeCondition) {
           this.conditions = removeCondition;
           this.x_coordinates = x_coordinates;
           this.y_coordinates = y_coordinates;
           this.angle = new Rotation2d(angle);
           this.xy_coordinates = new Translation2d(this.x_coordinates, this.y_coordinates);
        }
        /**
         * @return the angle we want the robot to face when reaching this point
         */
        private Rotation2d getAngle() {
            return this.angle;
        }
        /**
         * @return the position of the robot when reaching the POI
         */
        private Translation2d getCoordinates() {
            return this.xy_coordinates;
        }

        private boolean getConditionStatus() {
            return this.conditions.getAsBoolean();
        }
    }

    private PathConstraints constraints = new PathConstraints(
        3.0, 4.0,
        Units.degreesToRadians(540), Units.degreesToRadians(720));

             private LinkedList<Pose2d> point_list = new LinkedList<>();

    private Pose2d makepoint(LinkedList<POI> poi) {
        poi.removeIf(i -> (i.getConditionStatus()));

        for (POI pathPoint_poi : poi) {
        Pose2d pathPoint = new Pose2d(pathPoint_poi.getCoordinates(), pathPoint_poi.getAngle());
        point_list.add(pathPoint);
        }

        return new Pose2d(point_list.getFirst().getTranslation(), point_list.getFirst().getRotation());
    }

    public Command doPathfinding(LinkedList<POI> poi) {
        return Commands.run(() -> AutoBuilder.pathfindToPose(makepoint(poi), constraints));
    }
    
}
// this class should be in constants
final class ConditionBuilder {
    // example code to demonstrate how we could use functions to make our conditions
    public static boolean intakeHasNote() {
        boolean conditionFullfilled = false;
        boolean somecondition = true;
        if (somecondition == true) {
            conditionFullfilled = true;
        }
        return conditionFullfilled;
    }
}