interface StatCardProps {
  title: string;
  value: string;
}

const StatCard = ({
  title,
  value,
}: StatCardProps) => {
  return (
    <div className="surface-card p-6">
      <p className="text-sm text-text-secondary">
        {title}
      </p>

      <h3
        className="
          mt-2
          text-2xl
          font-bold
        "
      >
        {value}
      </h3>
    </div>
  );
};

export default StatCard;