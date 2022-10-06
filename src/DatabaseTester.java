import java.util.Scanner;

/** DatabaseTester is a test class that ensures
 *  database.java and its objects function properly
 *
 * @author drgazelle
 * @version V1.0
 */
public class DatabaseTester {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // creates database class and imports data.txt
        Database data = new Database();
        data.clear();

        data.add(new Course("Algebra"));
        data.add(new Course("Geometry"));
        data.add(new Course("Biology"));

        //Adds decks and flashcards to Algebra
        data.get(0).add(new Deck("Unit 1", "Properties of Exponents"));
        data.get(0).get(0).add(new Flashcard("ln 1", "0"));
        data.get(0).get(0).add(new Flashcard("ln e", "1"));

        data.get(0).add(new Deck("Unit 2", "Functions"));
        data.get(0).get(1).add(new Flashcard("point-slope form", "(y1 - y2) = m(x1 - x2)"));
        data.get(0).get(1).add(new Flashcard("standard form", "ax + by = c"));

        //Adds decks and flashcards to Geometry
        data.get(1).add(new Deck("Unit 1"));
        data.get(1).get(0).importQuizlet("Postulate/Axiom\tAn accepted statement of fact####Point\tindicates a location and has no size. Named with a capital letter.####Line\tA straight path extends in opposite ends and has no thickness. Contains infinitely many points.####Plane\tA flat surface that extends without end and has no thickness. Contains infinitely many lines.####Col-linear points\tPoints that lie on the same line####co-planar points\tpoints that lie on the same plane####line segment\tA part of a line between two endpoints and all points between them.####Ray\tA part of a line, with one endpoint, that continues without end in one direction and all points on one side of the end point.####Opposite Rays\tTwo rays that share the same endpoint and form a line####Postulate 1-1\tThrough any two points there is exactly one line####Postulate 1-2\tIf two distinct lines intersect, then they intersect in exactly one point.####Postulate 1-3\tIf two distinct planes intersect, then they intersect in exactly one line.####Postulate 1-4\tThrough any three noncollinear points there is exactly one plane####Coordinate\tThe real number that corresponds to a point####Postulate 1-5\tEvery point on a line can be paired with a real number####Postulate 1-6\tIf three points A, B, and C are collinear and B is between A and C, then AB+BC=AC####Distance Formula\td = √[( x₂ - x₁)² + (y₂ - y₁)²]####Midpoint formula\t(x₁+x₂)/2, (y₁+y₂)/2####Midpoint\tA point that divides a segment into two congruent segments####Bisect\tTo divide into two congruent parts####Bisector\tA point, line or line segment that divides a segment or angle into two equal parts.####Perpendicular bisector\tA segment, ray, line, or plane that is perpendicular to a segment at its midpoint####Angle bisector\ta ray that divides an angle into two congruent angles####Angle\tformed by two rays with the same endpoint####Sides of an angle\tThe rays of an angle####Vertex of the angle\tThe initial points of the rays####Interior of an angle\tThe region that contains all the points between the sides of an angle####Exterior of an angle\tThe region that contains all the points outside of an angle####Right angle\tAn angle that measures 90 degrees####Acute angle\tAn angle that is less than 90 degrees####Obtuse angle\tAn angle that measures more than 90 degrees but less than 180 degrees####Straight angle\tAn angle that measures exactly 180 degrees####Reflex angle\tBetween 180 and 360 degrees####Protractor Postulate\tGiven any angle, the measure can be put into one-to-one correspondence with real numbers between 0 and 180.####Congruent angles\tAngles that have the same measure####Angle Addition Postulate\tIf P is in the interior of <RST, then m<RSP + m<PST = m<RST####Adjacent Angles\ttwo angles that share a common vertex and side, but have no common interior points####Linear Pair\tA pair of adjacent angles whose non-common sides are opposite rays. The angles form a straight angle####Complementary Angles\tTwo angles that add up to 90 degrees. Each angle is called the compliment of the other.####Supplementary Angles\tTwo angles whose sum is 180 degrees. Each angle is called the supplement of the other.####Postulate 1-9\tIf two angles form a linear pair, then they are supplementary.####Inductive Reasoning\tA type of logic in which generalizations are based on a large number of specific observations.####Conjecture\tan opinion or conclusion formed on the basis of incomplete information####Counterexample\tan example used to support a claim or statement that is the opposite of another claim or statement####Conditional Statement\tA statement that can be written in if-then form. If p, then q.####Hypothesis\tA testable prediction, often implied by a theory####Conclusion\tA summary based on evidence or facts####Truth Value\tthe truth or falsity of a statement (T or F)####Converse\tIf q, then p (if conclusion, then hypothesis)####Inverse\tIf not p, then not q####Contrapositive\tthe statement formed by negating both the hypothesis and conclusion of the converse of a conditional statement####");

        //Adds decks and flashcards to Biology
        data.get(2).add(new Deck("Cells", "Science 7 HNs"));
        data.get(2).get(0).importQuizlet("Cell\tThe smallest unit that is able to preform basic functions of life.####Nucleus\tThe structure in a eukaryotic cell that contains the genetic material a cell needs to reproduce and function.####Cell Membrane\tThe outer boundary of the cytoplasm, a layer that controls what enters and leaves the cell; a protective covering enclosing the cell.####Cell Wall\tA protective outer-covering that lays just outside the membrane of a plant cell.####Cytoplasm\tA thick, gelatin like material contained within the cell membrane. Most of the work of the cell is carried out in the cytoplasm.####Organelle\tA structure in a cell that enclosed by a membrane and preforms a particular function.####Organ\tA structure in a plant or an animal that is made up of different tissues working together to preform a particular function.####Organism\tAn individual living thing, made up of one or many cells, that is capable of growing and reproducing.####Endoplasmic Recticulum (E.R)\tSystem of a membrane channels used for transport within the cells manufactures proteins and part of the cell membrane####Tissue\tA group to cells that are organized to do a specific job.####Organ System\tA group of organs that together preform a function that helps the body meet it's needs for energy and materials.####Chloroplast\tAn organelle in a plant cell where energy from sunlight is used to make sugar during the process of photosynthesis contain green pigments called chlorophyll.####Vacuole\tFluid-filled organelle enclosed by the membrane; holds water, waste, or other materials; works with the cell membrane to move materials into or out of the cell; a plant cell contains a larger central vacuole.####Mitochondrion\tOrganelle in both plant and animal cells that uses oxygen to get energy from food.####");
        data.exportDatabase();
    }
}
